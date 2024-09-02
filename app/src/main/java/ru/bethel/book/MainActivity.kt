package ru.bethel.book

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import ru.bethel.book.service.DownloadService
import ru.bethel.book.ui.drawer.DrawerLayout
import ru.bethel.book.ui.theme.darkGradient
import ru.bethel.book.ui.theme.lightGradient
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.newTestament
import ru.bethel.domain.model.oldTestament

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isLightMode = remember { mutableStateOf(false) }
            val isShowingDrawer = remember { mutableStateOf(true) }
            val mainViewModel = koinViewModel<MainViewModel>()
            FullScreenApp(isLightMode = isLightMode, isDrawerOpened = isShowingDrawer)
            startDownloadService(mainViewModel)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isLightMode.value) lightGradient else darkGradient)
            ) {
                DrawerLayout(
                    mainViewModel = mainViewModel,
                    isLightMode = isLightMode,
                    isShowingDrawer = isShowingDrawer
                )
            }
        }
    }

    private fun startDownloadService(mainViewModel: MainViewModel) {
        val chapters: List<Chapter> = mainViewModel.getChaptersToDownload()
        val intent = Intent(this, DownloadService::class.java)
        intent.putParcelableArrayListExtra("CHAPTERS", ArrayList(chapters))
        startService(intent)
    }

}


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
    systemUiController.isNavigationBarVisible = false
}
