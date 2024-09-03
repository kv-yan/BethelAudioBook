package ru.bethel.book.di

import android.content.Context
import org.koin.dsl.module
import ru.bethel.data.ThemeRepoImpl
import ru.bethel.domain.repo.ThemeRepo

val dataModule = module {
    single<ThemeRepo> { ThemeRepoImpl(get<Context>()) }
}
