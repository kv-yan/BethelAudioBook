package ru.bethel.book

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import ru.bethel.book.service.DownloadService
import ru.bethel.book.service.MediaPlaybackService
import ru.bethel.book.ui.drawer.DrawerLayout
import ru.bethel.book.ui.theme.darkGradient
import ru.bethel.book.ui.theme.lightGradient
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.book.view_model.ThemeViewModel
import ru.bethel.domain.model.Chapter

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission(this)
        setContent {
            val isShowingDrawer = remember { mutableStateOf(true) }
            val mainViewModel = koinViewModel<MainViewModel>()
            val themeViewModel = koinViewModel<ThemeViewModel>()
            val isLightMode = themeViewModel.isLightMode

            LaunchedEffect(isLightMode.value) {
                themeViewModel.setIsLightTheme(isLightMode.value)
            }

            FullScreenApp(isLightMode = isLightMode, isDrawerOpened = isShowingDrawer)
            startDownloadService(mainViewModel)
            createNotificationChannel(this)
            startMediaPlaybackService(this, MediaPlaybackService.ACTION_PLAY)

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
    }    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MediaPlaybackService.CHANNEL_ID,
                "Media Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }




    private fun startDownloadService(mainViewModel: MainViewModel) {
        val chapters: List<Chapter> = mainViewModel.getChaptersToDownload()
        val intent = Intent(this, DownloadService::class.java)
        intent.putParcelableArrayListExtra("CHAPTERS", ArrayList(chapters))
        startService(intent)
    }

    fun startMediaPlaybackService(context: Context, action: String) {
        val intent = Intent(context, MediaPlaybackService::class.java).apply {
            this.action = action
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }


    private val notificationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission is granted, you can start the foreground service
//            startAudioForegroundService()
        } else {
            // Handle permission denial
            Toast.makeText(this, "Notification permission required!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun requestNotificationPermission(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                notificationPermissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                // Permission already granted
//                startAudioForegroundService()
            }
        } else {
            // For Android versions below 13, start service without notification permission
//            startAudioForegroundService()
        }
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
    systemUiController.isNavigationBarVisible = true
    systemUiController.setNavigationBarColor(Color.Black)
}



