package ru.bethel.book.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bethel.book.R
import ru.bethel.domain.model.Chapter
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadService : Service() {
    private val TAG = "AudioDownloadService"

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private val notificationId = 1
    private val channelId = "DownloadChannel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

/*
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundService()
        val chapters: List<Chapter> = intent?.getParcelableArrayListExtra("CHAPTERS") ?: emptyList()
        coroutineScope.launch {
            downloadMp3Files(chapters)
        }
        return START_STICKY
    }
*/

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Download Notifications", NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Shows notifications when downloading MP3 files"
            }

            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

/*
    private fun startForegroundService() {
        notificationManager = NotificationManagerCompat.from(this)
        notificationBuilder =
            NotificationCompat.Builder(this, channelId).setContentTitle("Downloading MP3 files")
                .setSmallIcon(R.drawable.ic_download).setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOnlyAlertOnce(true).setCategory(NotificationCompat.CATEGORY_PROGRESS)

        startForeground(notificationId, notificationBuilder.build())
    }
*/

    private suspend fun downloadMp3Files(chapters: List<Chapter>) {
        val totalFiles = chapters.size
        var downloadedFiles = 0

        for (chapter in chapters) {
            val fileName = "${chapter.bookIndex}_${chapter.chapterIndex}.mp3"
            val file = File(getExternalFilesDir(null), fileName)
            if (!file.exists()) {
                try {
                    downloadFile(chapter.audioURL, file)
                    Log.e(TAG, "downloadMp3Files: ${chapter.audioURL} downloaded :: ${file.name}")
                    downloadedFiles++
                    updateProgress(downloadedFiles, totalFiles)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                downloadedFiles++
                updateProgress(downloadedFiles, totalFiles)
            }
        }
        stopSelf()
    }

    private suspend fun downloadFile(url: String, file: File) {
        withContext(Dispatchers.IO) {
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            try {
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val outputStream = FileOutputStream(file)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                outputStream.close()
                inputStream.close()
            } finally {
                urlConnection.disconnect()
            }
        }
    }

    private fun updateProgress(downloadedFiles: Int, totalFiles: Int) {
        val progress = (downloadedFiles * 100) / totalFiles
        notificationBuilder.setContentText("Downloaded $downloadedFiles of $totalFiles files")
            .setProgress(100, progress, false)
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundService()
        // Other logic here
        return START_STICKY
    }

    private fun startForegroundService() {
        notificationManager = NotificationManagerCompat.from(this)
        notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setContentTitle("Downloading MP3 files")
                .setSmallIcon(R.drawable.ic_download)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOnlyAlertOnce(true)
                .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                .setOngoing(true)

        startForeground(notificationId, notificationBuilder.build())
    }

}
