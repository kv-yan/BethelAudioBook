package ru.bethel.domain.usecase.book.set

import ru.bethel.domain.model.BookHead
import ru.bethel.domain.repo.BookRepo

class SetLastPlayedBookUseCase(private val bookRepo: BookRepo) {
    suspend operator fun invoke(book: BookHead) = bookRepo.setLastPlayedBook(book)
}