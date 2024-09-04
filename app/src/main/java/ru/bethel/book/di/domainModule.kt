package ru.bethel.book.di

import org.koin.dsl.module
import ru.bethel.domain.helpers.BookNavigationHelper
import ru.bethel.domain.helpers.DownloadHelper
import ru.bethel.domain.helpers.MediaPlayerHelper
import ru.bethel.domain.model.bibleBooks
import ru.bethel.domain.repo.BookRepo
import ru.bethel.domain.repo.ThemeRepo
import ru.bethel.domain.usecase.book.get.GetLastPlayedBookUseCase
import ru.bethel.domain.usecase.book.get.GetLastPlayedChapterUseCase
import ru.bethel.domain.usecase.book.set.SetLastPlayedBookUseCase
import ru.bethel.domain.usecase.book.set.SetLastPlayedChapterUseCase
import ru.bethel.domain.usecase.theme.GetIsLightThemeUseCase
import ru.bethel.domain.usecase.theme.SetIsLightThemeUseCase

val domainModule = module {
    single<GetIsLightThemeUseCase> { GetIsLightThemeUseCase(get<ThemeRepo>()) }
    single<SetIsLightThemeUseCase> { SetIsLightThemeUseCase(get<ThemeRepo>()) }

    single<GetLastPlayedBookUseCase> { GetLastPlayedBookUseCase(get<BookRepo>()) }
    single<SetLastPlayedBookUseCase> { SetLastPlayedBookUseCase(get<BookRepo>()) }
    single<GetLastPlayedChapterUseCase> { GetLastPlayedChapterUseCase(get<BookRepo>()) }
    single<SetLastPlayedChapterUseCase> { SetLastPlayedChapterUseCase(get<BookRepo>()) }

    single<MediaPlayerHelper> { MediaPlayerHelper(context = get()) }
    single<DownloadHelper> { DownloadHelper() }
    single<BookNavigationHelper> { BookNavigationHelper(bibleBooksList = bibleBooks, setLastPlayedBookUseCase = get<SetLastPlayedBookUseCase>(), setLastPlayedChapterUseCase = get<SetLastPlayedChapterUseCase>()) }



}