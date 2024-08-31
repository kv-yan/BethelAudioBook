package ru.bethel.book.view_model

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.newTestament
import ru.bethel.domain.model.oldTestament

private const val TAG = "url"

class MainViewModel : ViewModel() {
    private var inactivityJob: Job? = null

    val currentBook = mutableStateOf(newTestament.first())
    val currentChapter = mutableStateOf(currentBook.value.chapters.first())

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

    fun prepareMediaPlayer(audioUrl: String) {
        resetMediaPlayer()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
            setOnPreparedListener {
                totalDuration.intValue = it.duration
                isLoadedMP3.value = true
                startUpdatingCurrentTime()
            }
            setOnCompletionListener {
                // Reset player to the start when the MP3 ends
                it.seekTo(0)
                this@MainViewModel.currentPosition.floatValue = 0f
                this@MainViewModel.isPlaying.value = false

                // Start inactivity timer for chapter change
                startInactivityTimer()
            }
            setOnErrorListener { _, _, _ ->
                true
            }
            prepareAsync()
        }
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
                inactivityJob?.cancel() // Cancel inactivity timer on user interaction
            }
        } ?: Log.e(TAG, "play: MediaPlayer not initialized")
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying.value = false
                startInactivityTimer()
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
    }

    private fun startInactivityTimer() {
        inactivityJob?.cancel()
        inactivityJob = viewModelScope.launch {
            delay(10_000)
            onNextChapter()
        }
    }

    private fun resetMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying.value = false
        currentPosition.floatValue = 0f
        totalDuration.intValue = 0
        isLoadedMP3.value = false
        inactivityJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
