package com.example.android_kotlin.services.startedService

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast

/** Started Service */
class MyStartedService : Service() {
    private val list = ArrayList<MediaPlayer>()
    override fun onCreate() {
        Toast.makeText(applicationContext, "onCreate()", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(applicationContext, "onStartCommand()", Toast.LENGTH_SHORT).show()

        val mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        list.add(mediaPlayer)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(applicationContext, "onDestroy()", Toast.LENGTH_SHORT).show()
        for (player in list){
            player.stop()
        }
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Toast.makeText(applicationContext, "onBind()", Toast.LENGTH_SHORT).show()

        return null
    }


}