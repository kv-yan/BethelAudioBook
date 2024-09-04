package ru.bethel.book.di

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.book.view_model.ThemeViewModel
import ru.bethel.domain.helpers.BookNavigationHelper
import ru.bethel.domain.helpers.DownloadHelper
import ru.bethel.domain.helpers.MediaPlayerHelper
import ru.bethel.domain.usecase.book.get.GetLastPlayedBookUseCase
import ru.bethel.domain.usecase.book.get.GetLastPlayedChapterUseCase
import ru.bethel.domain.usecase.book.set.SetLastPlayedBookUseCase
import ru.bethel.domain.usecase.book.set.SetLastPlayedChapterUseCase
import ru.bethel.domain.usecase.theme.GetIsLightThemeUseCase
import ru.bethel.domain.usecase.theme.SetIsLightThemeUseCase

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            context = get<Context>(),
            getLastPlayedBookUseCase = get<GetLastPlayedBookUseCase>(),
            getLastPlayedChapterUseCase = get<GetLastPlayedChapterUseCase>(),
            mediaPlayerHelper = get<MediaPlayerHelper>(),
            downloadHelper = get<DownloadHelper>(),
            bookNavigationHelper = get<BookNavigationHelper>()
        )
    }

    viewModel<ThemeViewModel> {
        ThemeViewModel(
            getIsLightThemeUseCase = get<GetIsLightThemeUseCase>(),
            setIsLightThemeUseCase = get<SetIsLightThemeUseCase>()
        )
    }
}


