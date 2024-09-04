package ru.bethel.book.view_model

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.bethel.domain.helpers.BookNavigationHelper
import ru.bethel.domain.helpers.DownloadHelper
import ru.bethel.domain.helpers.MediaPlayerHelper
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.newTestament
import ru.bethel.domain.usecase.book.get.GetLastPlayedBookUseCase
import ru.bethel.domain.usecase.book.get.GetLastPlayedChapterUseCase
import java.io.File


class MainViewModel(
    private val context: Context,
    private val getLastPlayedChapterUseCase: GetLastPlayedChapterUseCase,
    private val getLastPlayedBookUseCase: GetLastPlayedBookUseCase,
    private val mediaPlayerHelper: MediaPlayerHelper,
    private val downloadHelper: DownloadHelper,
    private val bookNavigationHelper: BookNavigationHelper
) : ViewModel() {

    private var inactivityJob: Job? = null

    val currentBook = mutableStateOf<BookHead>(newTestament.first()).apply {
        viewModelScope.launch {
            value = getLastPlayedBookUseCase.invoke()
        }
    }
    val currentChapter = mutableStateOf<Chapter>(currentBook.value.chapters.first()).apply {
        viewModelScope.launch {
            value = getLastPlayedChapterUseCase.invoke()
        }
    }

    val isPlaying = mediaPlayerHelper.isPlaying
    val currentPosition = mediaPlayerHelper.currentPosition
    val totalDuration = mediaPlayerHelper.totalDuration
    val isLoadedMP3 = mediaPlayerHelper.isLoadedMP3
    val mediaPlayer = mediaPlayerHelper.mediaPlayer

    fun onNextChapter() {
        bookNavigationHelper.onNextChapter(
            viewModelScope = viewModelScope,
            currentBook = currentBook,
            currentChapter = currentChapter
        )
    }

    fun onPreviousChapter() {
        bookNavigationHelper.onPreviousChapter(
            viewModelScope = viewModelScope,
            currentBook = currentBook,
            currentChapter = currentChapter
        )
    }

    fun prepareMediaPlayer() {
        val chapter = currentChapter.value
        val fileName = "${chapter.bookIndex}_${chapter.chapterIndex}.mp3"
        val file = File(context.getExternalFilesDir(null), fileName)
        val onCompletion = {
            onNextChapter()
        }

        if (file.exists()) {
            mediaPlayerHelper.prepareMediaPlayer(file.path, viewModelScope, onCompletion)
        } else {
            downloadHelper.downloadFile(viewModelScope, chapter.audioURL, file) {
                mediaPlayerHelper.prepareMediaPlayer(file.path, viewModelScope, onCompletion)
            }
        }
    }

    fun play() = mediaPlayerHelper.play()

    fun pause() = mediaPlayerHelper.pause()

    fun skipForward10Seconds() = mediaPlayerHelper.skipForward10Seconds()

    fun skipBackward10Seconds() = mediaPlayerHelper.skipBackward10Seconds()

    fun seekToPosition(position: Int) = mediaPlayerHelper.seekToPosition(position)

    fun getChaptersToDownload() = bookNavigationHelper.getChaptersToDownload()

    fun getPreviousBook() = bookNavigationHelper.getPreviousBook(currentBook.value)

    fun saveChanges() {
        viewModelScope.launch {
            bookNavigationHelper.saveLastPlayed(
                viewModelScope = viewModelScope,
                book = currentBook.value,
                chapter = currentChapter.value
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayerHelper.release()
        inactivityJob?.cancel()
    }

}
