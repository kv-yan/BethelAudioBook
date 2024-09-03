package ru.bethel.domain.usecase.theme

import ru.bethel.domain.repo.ThemeRepo

class GetIsLightThemeUseCase (private val themeRepo: ThemeRepo) {
    suspend fun execute(): Boolean  = themeRepo.isLightMode()
}