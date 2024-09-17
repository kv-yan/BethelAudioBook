package ru.bethel.book.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import ru.bethel.book.R
import ru.bethel.domain.helpers.MediaPlayerHelper


class MediaPlaybackService : Service() {

    private lateinit var mediaPlayerHelper: MediaPlayerHelper
    private lateinit var notificationManager: NotificationManager
    private lateinit var binder: IBinder

    override fun onCreate() {
        super.onCreate()
        mediaPlayerHelper = MediaPlayerHelper(this)
        binder = MediaPlaybackBinder()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class MediaPlaybackBinder : Binder() {
        fun getService(): MediaPlaybackService = this@MediaPlaybackService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        when (action) {
            ACTION_PLAY -> play()
            ACTION_PAUSE -> pause()
            ACTION_SKIP_FORWARD -> mediaPlayerHelper.skipForward10Seconds()
            ACTION_SKIP_BACKWARD -> mediaPlayerHelper.skipBackward10Seconds()
        }
        return START_NOT_STICKY
    }

    private fun play() {
        mediaPlayerHelper.play()
        showNotification()
    }

    private fun pause() {
        mediaPlayerHelper.pause()
        showNotification()
    }

    private fun showNotification() {
        val playPauseAction = if (mediaPlayerHelper.isPlaying.value) {
            NotificationCompat.Action(
                R.drawable.ic_player_pause,
                "Pause",
                getServiceIntent(ACTION_PAUSE)
            )
        } else {
            NotificationCompat.Action(
                R.drawable.ic_player_play,
                "Play",
                getServiceIntent(ACTION_PLAY)
            )
        }

        val mediaSession = MediaSessionCompat(this, "MediaPlaybackService")
        mediaSession.isActive = true
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS)

        val mediaStyle = androidx.media.app.NotificationCompat.MediaStyle()
            .setShowActionsInCompactView(0, 1, 2)
            .setMediaSession(mediaSession.sessionToken)


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.bethel_logo_dark)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.bethel_logo_dark))
            .addAction(
                NotificationCompat.Action(
                    R.drawable.ic_player_prev,
                    "Back",
                    getServiceIntent(ACTION_SKIP_BACKWARD)
                )
            )
            .addAction(playPauseAction)
            .addAction(
                NotificationCompat.Action(
                    R.drawable.ic_player_next,
                    "Next",
                    getServiceIntent(ACTION_SKIP_FORWARD)
                )
            )
            .setStyle(mediaStyle)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .build()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun getServiceIntent(action: String): PendingIntent {
        val intent = Intent(this, MediaPlaybackService::class.java).apply {
            this.action = action
        }

        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        return PendingIntent.getService(this, 0, intent, flags)
    }

    override fun onDestroy() {
        mediaPlayerHelper.release()
        stopForeground(true)
        super.onDestroy()
    }

    companion object {
        const val ACTION_PLAY = "ru.bethel.book.app.ACTION_PLAY"
        const val ACTION_PAUSE = "ru.bethel.book.app.ACTION_PAUSE"
        const val ACTION_SKIP_FORWARD = "ru.bethel.book.app.ACTION_SKIP_FORWARD"
        const val ACTION_SKIP_BACKWARD = "ru.bethel.book.app.ACTION_SKIP_BACKWARD"
        const val CHANNEL_ID = "media_playback_channel"
        const val NOTIFICATION_ID = 1
    }
}
