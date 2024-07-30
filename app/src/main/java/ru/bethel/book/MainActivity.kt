package ru.bethel.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.bethel.book.ui.actionBar.HomeActionBar
import ru.bethel.book.ui.screens.HomeScreen
import ru.bethel.book.ui.theme.darkGradient
import ru.bethel.book.ui.theme.lightGradient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var isLightMode = remember { mutableStateOf(false) }
            FullScreenApp(isLightMode)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isLightMode.value) lightGradient else darkGradient)
            ) {
                Column {
                    HomeActionBar(isLightMode.value, onMenuClick = {/* open*/ }) {
                        isLightMode.value = !isLightMode.value
                    }
                    HomeScreen(isLightMode)
                }

            }
        }
    }
}

@Composable
fun FullScreenApp(isLightMode: MutableState<Boolean>) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isLightMode.value


    val topStatusBarColor = if (isLightMode.value) Color(0xFFFFF1F8) else Color(0xFF00102F)
    systemUiController.setSystemBarsColor(
        color = topStatusBarColor, darkIcons = useDarkIcons
    )

    systemUiController.isStatusBarVisible = true
    systemUiController.isNavigationBarVisible = false
}
