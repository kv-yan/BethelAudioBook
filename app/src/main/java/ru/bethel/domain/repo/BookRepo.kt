package ru.bethel.domain.repo

import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter

interface BookRepo {
    suspend fun getLastPlayedBook(): BookHead

    suspend fun setLastPlayedBook(book: BookHead)

    suspend fun getLastPlayedChapter(): Chapter

    suspend fun setLastPlayedChapter(chapter: Chapter)
}