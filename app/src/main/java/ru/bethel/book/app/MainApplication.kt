package ru.bethel.book.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.bethel.book.di.appModule
import ru.bethel.book.di.dataModule
import ru.bethel.book.di.domainModule
import ru.bethel.book.service.MediaPlaybackService

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(this)
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, dataModule, domainModule)
        }
    }

    private fun createNotificationChannel(context: Context) {
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

}