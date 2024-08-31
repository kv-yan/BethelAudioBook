package ru.bethel.book.view_model

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.newTestament
import ru.bethel.domain.model.oldTestament
import ru.bethel.domain.model.ui.AudioData

private const val TAG = "url"

class MainViewModel : ViewModel() {
    val currentBook = mutableStateOf<BookHead>(newTestament.first())
    val currentChapter = mutableStateOf<Chapter>(currentBook.value.chapters.first())

    val bibleBooksList = mutableListOf<BookHead>().apply {
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
        } else if(currentChapterIndex == 0) {
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
    private val audioData = mutableStateOf(AudioData("0:00", "0:00"))
    val currentPosition = mutableFloatStateOf(0f)
    private val totalDuration = mutableIntStateOf(0)

    val isLoadedMP3 = mutableStateOf(false)

    fun prepareMediaPlayer(context: Context, audioUrl: String, retryCount: Int = 3) {
        resetMediaPlayer()  // Reset the player before loading new data
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
            setOnPreparedListener {
                totalDuration.value = it.duration
                audioData.value = AudioData(
                    currentSeconds = formatTime(0), totalSeconds = formatTime(it.duration / 1000)
                )
                isLoadedMP3.value = true
                startUpdatingCurrentTime()
            }
            setOnErrorListener { _, what, extra ->
                Log.e(TAG, "Error occurred: $what, $extra")
                if ((what == 1 && extra == -1005) || retryCount > 0) { // IO Error
                    Log.e(TAG, "Retrying media loading...")
                    prepareMediaPlayer(context, audioUrl, retryCount - 1) // Retry
                } else {
                    resetMediaPlayer()
                }
                true // Indicate that the error was handled
            }
            prepareAsync() // Prepare the player asynchronously
        }
    }

    private fun resetMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying.value = false
        currentPosition.value = 0f
        totalDuration.value = 0
        audioData.value = AudioData("0:00", "0:00")
        isLoadedMP3.value = false // Reset MP3 load status
    }

    private fun startUpdatingCurrentTime() {
        viewModelScope.launch {
            while (true) {
                mediaPlayer?.let { player ->
                    if (player.isPlaying) {
                        currentPosition.floatValue = player.currentPosition.toFloat()
                        audioData.value = audioData.value.copy(
                            currentSeconds = formatTime(player.currentPosition / 1000)
                        )
                    }
                }
                delay(1000)
            }
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%d:%02d", minutes, remainingSeconds)
    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
                isPlaying.value = true
            }
        } ?: Log.e(TAG, "play: MediaPlayer not initialized")
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying.value = false
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

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
