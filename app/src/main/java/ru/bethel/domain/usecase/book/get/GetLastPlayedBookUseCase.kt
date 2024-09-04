package ru.bethel.domain.usecase.book.get

import ru.bethel.domain.repo.BookRepo

class GetLastPlayedBookUseCase(private val bookRepo: BookRepo) {
    suspend operator fun invoke() = bookRepo.getLastPlayedBook()
}
