package ru.bethel.book.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bethel.domain.usecase.theme.GetIsLightThemeUseCase
import ru.bethel.domain.usecase.theme.SetIsLightThemeUseCase

class ThemeViewModel(
    private val getIsLightThemeUseCase: GetIsLightThemeUseCase,
    private val setIsLightThemeUseCase: SetIsLightThemeUseCase
) : ViewModel() {

    val isLightMode = mutableStateOf(getIsLightTheme())

    fun getIsLightTheme(): Boolean {
        var isLightTheme: Boolean = true

        viewModelScope.launch {
            isLightTheme = getIsLightThemeUseCase.execute()
        }

        return isLightTheme
    }

    fun setIsLightTheme(isLightTheme: Boolean) {
        viewModelScope.launch {
            setIsLightThemeUseCase.execute(isLightTheme)
        }

    }
}