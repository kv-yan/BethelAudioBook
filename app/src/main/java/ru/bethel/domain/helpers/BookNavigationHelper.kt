package ru.bethel.domain.helpers

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.usecase.book.set.SetLastPlayedBookUseCase
import ru.bethel.domain.usecase.book.set.SetLastPlayedChapterUseCase

class BookNavigationHelper(
    private val bibleBooksList: List<BookHead>,
    private val setLastPlayedBookUseCase: SetLastPlayedBookUseCase,
    private val setLastPlayedChapterUseCase: SetLastPlayedChapterUseCase
) {

    fun onNextChapter(
        viewModelScope: CoroutineScope,
        currentBook: MutableState<BookHead>,
        currentChapter: MutableState<Chapter>
    ) {
        val currentChapterIndex = currentBook.value.chapters.indexOf(currentChapter.value)
        if (currentChapterIndex < currentBook.value.chapters.size - 1) {
            currentChapter.value = currentBook.value.chapters[currentChapterIndex + 1]
        } else if (currentChapterIndex == currentBook.value.chapters.size - 1) {
            val currentBookIndex = bibleBooksList.indexOf(currentBook.value)
            if (currentBookIndex < bibleBooksList.size - 1) {
                currentBook.value = bibleBooksList[currentBookIndex + 1]
                currentChapter.value = currentBook.value.chapters.first()
            }
        }
        saveLastPlayed(
            viewModelScope = viewModelScope,
            book = currentBook.value,
            chapter = currentChapter.value
        )
    }

    fun onPreviousChapter(
        viewModelScope: CoroutineScope,
        currentBook: MutableState<BookHead>,
        currentChapter: MutableState<Chapter>
    ) {
        val currentChapterIndex = currentBook.value.chapters.indexOf(currentChapter.value)

        if (currentChapterIndex > 0) {
            currentChapter.value = currentBook.value.chapters[currentChapterIndex - 1]
        } else if (currentChapterIndex == 0) {
            val currentBookIndex = bibleBooksList.indexOf(currentBook.value)
            if (currentBookIndex > 0) {
                currentBook.value = bibleBooksList[currentBookIndex - 1]
                currentChapter.value = currentBook.value.chapters.last()
            }
        }
        saveLastPlayed(
            viewModelScope = viewModelScope,
            book = currentBook.value,
            chapter = currentChapter.value
        )
    }

    fun getChaptersToDownload(): List<Chapter> {
        return bibleBooksList.flatMap { it.chapters }
    }

    fun getPreviousBook(currentBook: BookHead): BookHead? {
        return bibleBooksList.getOrNull(bibleBooksList.indexOf(currentBook) - 1)
    }

    fun saveLastPlayed(viewModelScope: CoroutineScope, book: BookHead, chapter: Chapter) {
        viewModelScope.launch(Dispatchers.IO) {
            setLastPlayedBookUseCase(book)
            setLastPlayedChapterUseCase(chapter)
        }
    }
}
