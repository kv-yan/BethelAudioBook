package ru.bethel.data

import android.content.Context
import ru.bethel.domain.IS_LIGHT_MODE
import ru.bethel.domain.repo.ThemeRepo

class ThemeRepoImpl(private val context: Context) : ThemeRepo {
    private val sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    override suspend fun isLightMode(): Boolean = sharedPreferences.getBoolean(IS_LIGHT_MODE, true)

    override suspend fun setLightMode(isLightMode: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LIGHT_MODE, isLightMode).apply()
    }
}