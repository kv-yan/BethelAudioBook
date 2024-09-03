package ru.bethel.book.di

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.book.view_model.ThemeViewModel
import ru.bethel.data.ThemeRepoImpl
import ru.bethel.domain.repo.ThemeRepo
import ru.bethel.domain.usecase.theme.GetIsLightThemeUseCase
import ru.bethel.domain.usecase.theme.SetIsLightThemeUseCase

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            context = get<Context>()
        )
    }

    viewModel<ThemeViewModel> {
        ThemeViewModel(
            getIsLightThemeUseCase = get<GetIsLightThemeUseCase>(),
            setIsLightThemeUseCase = get<SetIsLightThemeUseCase>()
        )
    }
}


