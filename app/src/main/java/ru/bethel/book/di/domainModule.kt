package ru.bethel.book.di

import org.koin.dsl.module
import ru.bethel.domain.repo.ThemeRepo
import ru.bethel.domain.usecase.theme.GetIsLightThemeUseCase
import ru.bethel.domain.usecase.theme.SetIsLightThemeUseCase

val domainModule = module {
    single<GetIsLightThemeUseCase> { GetIsLightThemeUseCase(get<ThemeRepo>()) }
    single<SetIsLightThemeUseCase> { SetIsLightThemeUseCase(get<ThemeRepo>()) }
}