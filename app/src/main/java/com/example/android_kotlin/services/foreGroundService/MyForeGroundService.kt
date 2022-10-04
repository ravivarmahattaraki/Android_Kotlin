package com.example.android_kotlin.services.foreGroundService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.android_kotlin.R
import com.example.android_kotlin.services.ServiceExampleActivity

class MyForeGroundService : Service() {
    companion object{
        val REQUEST_CODE = 100
        val CHANNEL_ID = "MyForeGroundServiceChannel"
    }
    fun showNotification(){
        val notificationIntent = Intent(this, ServiceExampleActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE)

        val notificationChannel = NotificationChannel(CHANNEL_ID, "channel",
            NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("MY_FOREGROUND")
            .setContentText("App running in background")
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
    }

    override fun onCreate() {
        Toast.makeText(this, "onCreate",Toast.LENGTH_SHORT).show()

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this, "onStartCommand",Toast.LENGTH_SHORT).show()

        showNotification()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "onDestroy",Toast.LENGTH_SHORT).show()

        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}