package ru.bethel.book.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AudioPlayerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "ACTION_PLAY_PAUSE" -> {
                // Handle play/pause action
            }
            "ACTION_REWIND" -> {
                // Handle rewind 10 seconds action
            }
            "ACTION_FORWARD" -> {
                // Handle forward 10 seconds action
            }
            "ACTION_NEXT" -> {
                // Handle next track action
            }
            "ACTION_PREV" -> {
                // Handle previous track action
            }
        }
    }
}
