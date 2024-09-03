package ru.bethel.domain.repo

interface ThemeRepo {

    suspend fun isLightMode(): Boolean

    suspend fun setLightMode(isLightMode: Boolean)

}