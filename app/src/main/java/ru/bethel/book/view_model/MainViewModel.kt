package ru.bethel.book.view_model

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.newTestament
import ru.bethel.domain.model.oldTestament
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "url"

class MainViewModel(private val context: Context) : ViewModel() {

    private var inactivityJob: Job? = null

    val currentBook = mutableStateOf<BookHead>(newTestament.first())
    val currentChapter = mutableStateOf<Chapter>(currentBook.value.chapters.first())

    private val bibleBooksList = mutableListOf<BookHead>().apply {
        addAll(oldTestament)
        addAll(newTestament)
    }

    fun onNextChapter() {
        val currentChapterIndex = currentBook.value.chapters.indexOf(currentChapter.value)
        if (currentChapterIndex < currentBook.value.chapters.size - 1) {
            // next chapter exists, update currentChapter
            currentChapter.value = currentBook.value.chapters[currentChapterIndex + 1]
        } else if (currentChapterIndex == currentBook.value.chapters.size - 1) {
            // last chapter of the book
            // move to next book
            val currentBookIndex = bibleBooksList.indexOf(currentBook.value)
            if (currentBookIndex < bibleBooksList.size - 1) {
                currentBook.value = bibleBooksList[currentBookIndex + 1]
                currentChapter.value = currentBook.value.chapters.first()
            }
        }
    }

    fun onPreviousChapter() {
        val currentChapterIndex = currentBook.value.chapters.indexOf(currentChapter.value)
        if (currentChapterIndex != 0 && (currentChapterIndex < currentBook.value.chapters.size - 1)) {
            // next chapter exists, update currentChapter
            currentChapter.value = currentBook.value.chapters[currentChapterIndex - 1]
        } else if (currentChapterIndex == currentBook.value.chapters.size - 1) {
            // last chapter of the book
            // move to next book
            val currentBookIndex = bibleBooksList.indexOf(currentBook.value)
            if (currentBookIndex < bibleBooksList.size - 1) {
                currentBook.value = bibleBooksList[currentBookIndex + 1]
                currentChapter.value = currentBook.value.chapters.first()
            }
        } else if (currentChapterIndex == 0) {
            // first chapter of the book
            // move to previous book
            val currentBookIndex = bibleBooksList.indexOf(currentBook.value)
            if (currentBookIndex > 0) {
                currentBook.value = bibleBooksList[currentBookIndex - 1]
                currentChapter.value = currentBook.value.chapters.last()
            }

        }
    }

    var mediaPlayer: MediaPlayer? = null
    val isPlaying = mutableStateOf(false)
    val currentPosition = mutableFloatStateOf(0f)
    val totalDuration = mutableIntStateOf(0)
    val isLoadedMP3 = mutableStateOf(false)

    fun prepareMediaPlayer() {
        val chapter = currentChapter.value
        val fileName = "${chapter.bookIndex}_${chapter.chapterIndex}.mp3"
        val file = File(context.getExternalFilesDir(null), fileName)

        if (file.exists()) {
            // Load from local storage
            prepareMediaPlayerFromFile(file.path)
            Log.e(TAG, "prepareMediaPlayer: loaded from local")
        } else {
            // Download from server and then load
            downloadFile(chapter.audioURL, file) {
                prepareMediaPlayerFromFile(file.path)
            }
        }
    }

    private fun prepareMediaPlayerFromFile(filePath: String) {
        resetMediaPlayer()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(filePath)
            setOnPreparedListener {
                totalDuration.intValue = it.duration
                isLoadedMP3.value = true
                startUpdatingCurrentTime()
            }
            setOnCompletionListener {
                it.seekTo(0)
                this@MainViewModel.currentPosition.value = 0f
                this@MainViewModel.isPlaying.value = false
                startInactivityTimer()
            }
            setOnErrorListener { _, _, _ ->
                true
            }
            prepareAsync()
        }
    }

    private fun downloadFile(url: String, file: File, onDownloadComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val urlConnection = URL(url).openConnection() as HttpURLConnection
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val outputStream = FileOutputStream(file)

                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                outputStream.close()
                inputStream.close()
                urlConnection.disconnect()

                // Notify that the download is complete
                withContext(Dispatchers.Main) {
                    onDownloadComplete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the error appropriately, e.g., show a notification or log it
            }
        }
    }

    private fun startInactivityTimer() {
        inactivityJob?.cancel()
        inactivityJob = viewModelScope.launch {
            delay(10_000) // 10 seconds of inactivity
            onNextChapter() // Change to the next chapter after 10 seconds
        }
    }

    private fun resetMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying.value = false
        currentPosition.value = 0f
        totalDuration.value = 0
        isLoadedMP3.value = false
        inactivityJob?.cancel()
    }

    private fun startUpdatingCurrentTime() {
        viewModelScope.launch {
            while (true) {
                mediaPlayer?.let { player ->
                    if (player.isPlaying) {
                        currentPosition.floatValue = player.currentPosition.toFloat()
                    }
                }
                delay(1000)
            }
        }
    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
                isPlaying.value = true
                inactivityJob?.cancel()
            }
        } ?: Log.e(TAG, "play: MediaPlayer not initialized")
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying.value = false
                inactivityJob?.cancel()
            }
        } ?: Log.e(TAG, "pause: MediaPlayer not initialized")
    }

    fun skipForward10Seconds() {
        mediaPlayer?.let { player ->
            val newPosition = player.currentPosition + 10000
            player.seekTo(newPosition.coerceAtMost(player.duration))
        }
    }

    fun skipBackward10Seconds() {
        mediaPlayer?.let { player ->
            val newPosition = player.currentPosition - 10000
            player.seekTo(newPosition.coerceAtLeast(0))
        }
    }

    fun seekToPosition(position: Int) {
        mediaPlayer?.seekTo(position)
        currentPosition.floatValue = position.toFloat()
        if (mediaPlayer?.isPlaying == false) {
            play()
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
        inactivityJob?.cancel()
    }

    fun getChaptersToDownload(): List<Chapter> {
        val chaptersToDownload = mutableListOf<Chapter>()
        bibleBooksList.forEach { book ->
            book.chapters.forEach { chapter ->
                chaptersToDownload.add(chapter)
            }
        }
        return chaptersToDownload
    }

    fun getPreviousBook(): BookHead {
        return bibleBooksList[bibleBooksList.indexOf(currentBook.value) - 1]
    }

}
