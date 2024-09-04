package ru.bethel.domain.usecase.book.set

import ru.bethel.domain.model.Chapter
import ru.bethel.domain.repo.BookRepo

class SetLastPlayedChapterUseCase(private val bookRepo: BookRepo) {
    suspend operator fun invoke(chapter: Chapter) = bookRepo.setLastPlayedChapter(chapter)
}