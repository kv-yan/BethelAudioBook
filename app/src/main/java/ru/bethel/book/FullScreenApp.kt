package ru.bethel.book

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun FullScreenApp(isLightMode: MutableState<Boolean>, isDrawerOpened: MutableState<Boolean>) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isLightMode.value

    val topStatusBarColor = if (isDrawerOpened.value) {
        if (isLightMode.value) Color(0xFFF5F9FB) else Color(0xFF000000)
    } else {
        if (isLightMode.value) Color(0xFFFEFBFE) else Color(0xFF000000)
    }
    systemUiController.setSystemBarsColor(
        color = topStatusBarColor, darkIcons = useDarkIcons
    )

    systemUiController.isStatusBarVisible = true
    systemUiController.isNavigationBarVisible = true
    systemUiController.setNavigationBarColor(Color.Black)
}
