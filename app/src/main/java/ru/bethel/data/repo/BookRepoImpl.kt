package ru.bethel.data.repo

import android.content.Context
import android.util.Log
import ru.bethel.domain.LAST_PLAYED_BOOK
import ru.bethel.domain.LAST_PLAYED_CHAPTER
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.bibleBooks
import ru.bethel.domain.repo.BookRepo

private const val TAG = "SIZE"

class BookRepoImpl(context: Context) : BookRepo {
    private val sharedPreferences = context.getSharedPreferences("book", Context.MODE_PRIVATE)

    override suspend fun getLastPlayedBook(): BookHead {
        val index = sharedPreferences.getInt(LAST_PLAYED_BOOK, 0)
        return bibleBooks.find { it.bookIndex == index } ?: bibleBooks[0]
    }

    override suspend fun setLastPlayedBook(book: BookHead) {
        sharedPreferences.edit().putInt(LAST_PLAYED_BOOK, book.bookIndex).apply()
    }

    override suspend fun getLastPlayedChapter(): Chapter {
        val index = sharedPreferences.getInt(LAST_PLAYED_CHAPTER, 0)
        Log.e(TAG, "getLastPlayedBook: ${bibleBooks.size}")
        return getLastPlayedBook().chapters.find { it.chapterIndex == index }
            ?: getLastPlayedBook().chapters[0]
    }

    override suspend fun setLastPlayedChapter(chapter: Chapter) {
        sharedPreferences.edit().putInt(LAST_PLAYED_CHAPTER, chapter.chapterIndex).apply()
    }
}