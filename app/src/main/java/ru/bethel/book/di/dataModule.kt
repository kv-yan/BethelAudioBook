package ru.bethel.book.di

import android.content.Context
import org.koin.dsl.module
import ru.bethel.data.repo.BookRepoImpl
import ru.bethel.data.repo.ThemeRepoImpl
import ru.bethel.domain.repo.BookRepo
import ru.bethel.domain.repo.ThemeRepo

val dataModule = module {
    single<ThemeRepo> { ThemeRepoImpl(get<Context>()) }

    single<BookRepo> { BookRepoImpl(get<Context>()) }
}
