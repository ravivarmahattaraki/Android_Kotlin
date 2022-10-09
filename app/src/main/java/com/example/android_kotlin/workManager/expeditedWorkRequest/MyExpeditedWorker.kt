package com.example.android_kotlin.workManager.expeditedWorkRequest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.android_kotlin.R
import com.example.android_kotlin.services.ServiceExampleActivity
import com.google.common.util.concurrent.ListenableFuture

/** Expedited Work with notification*/
class MyExpeditedWorker(var context : Context, var params : WorkerParameters) : Worker(context,params) {
    companion object{
        val ONE_TIME_WORKER = "ONE_TIME_WORKER"
        val PROGRESS = "PROGRESS"
        val REQUEST_CODE = 100
        val CHANNEL_ID = "MyExpeditedWorker"
    }
    override fun getForegroundInfoAsync(): ListenableFuture<ForegroundInfo> {
        super.getForegroundInfoAsync()
        val future : ListenableFuture<ForegroundInfo> =
            CallbackToFutureAdapter.getFuture{
            it.set(ForegroundInfo(REQUEST_CODE, showNotification()))
        }
        return future
    }

    override fun doWork(): Result {
        sendBroadCast("Work started")
        Thread.sleep(2000)

        for(i in 1..10){
            Thread.sleep(1000)
            sendBroadCast("Progress ${i*10} % complete")
        }

        Thread.sleep(1000)
        sendBroadCast("Work finished")
        return Result.success()
    }

    fun sendBroadCast(message : String){
        val intent = Intent(ONE_TIME_WORKER)
        intent.putExtra(PROGRESS, message)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

    fun showNotification() : Notification{
        val notificationIntent = Intent(context, ServiceExampleActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,
            REQUEST_CODE, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE)

        val notificationChannel = NotificationChannel(CHANNEL_ID,
            "channel",
            NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(context,NotificationManager::class.java)
        notificationManager?.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Expedited Work")
            .setContentText("Expedited Work running")
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setContentIntent(pendingIntent)
            .build()
        
        return notification
    }
}