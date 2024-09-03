package ru.bethel.domain.usecase.theme

import ru.bethel.domain.repo.ThemeRepo

class SetIsLightThemeUseCase (private val themeRepo: ThemeRepo) {
    suspend fun execute(isLightMode: Boolean) = themeRepo.setLightMode(isLightMode)
}