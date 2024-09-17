package ru.bethel.domain.helpers

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MediaPlayerHelper(
    private val context: Context, ) {

    var mediaPlayer: MediaPlayer? = null
    val isPlaying = mutableStateOf(false)
    val currentPosition = mutableFloatStateOf(0f)
    val totalDuration = mutableIntStateOf(0)
    val isLoadedMP3 = mutableStateOf(false)

    fun prepareMediaPlayer(filePath: String, scope: CoroutineScope, onCompletion: () -> Unit) {
        resetMediaPlayer()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(filePath)
            setOnPreparedListener {
                totalDuration.intValue = it.duration
                isLoadedMP3.value = true
                startUpdatingCurrentTime(scope) {
                    this@MediaPlayerHelper.currentPosition.floatValue =
                        mediaPlayer?.currentPosition?.toFloat() ?: 0f
                }
            }
            setOnCompletionListener {
                it.seekTo(0)
                this@MediaPlayerHelper.currentPosition.value = 0f
                this@MediaPlayerHelper.isPlaying.value = false
                onCompletion()
            }
            setOnErrorListener { _, _, _ ->
                true
            }
            prepareAsync()
        }
    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
                isPlaying.value = true
            }
        }
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying.value = false
            }
        }
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
        currentPosition.value = position.toFloat()
        if (mediaPlayer?.isPlaying == false) {
            play()
        }
    }

    private fun resetMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying.value = false
        currentPosition.value = 0f
        totalDuration.value = 0
        isLoadedMP3.value = false
    }

    private fun startUpdatingCurrentTime(
        viewModelScope: CoroutineScope,
        onPositionUpdate: (Float) -> Unit
    ) {
        viewModelScope.launch {
            while (true) {
                mediaPlayer?.let { player ->
                    if (player.isPlaying) {
                        onPositionUpdate(player.currentPosition.toFloat())
                    }
                }
                delay(1000)
            }
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
