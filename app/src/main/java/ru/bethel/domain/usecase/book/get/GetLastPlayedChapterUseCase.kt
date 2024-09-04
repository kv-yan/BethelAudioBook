package ru.bethel.domain.usecase.book.get

import ru.bethel.domain.repo.BookRepo

class GetLastPlayedChapterUseCase(private val bookRepo: BookRepo) {
    suspend operator fun invoke() = bookRepo.getLastPlayedChapter()
}