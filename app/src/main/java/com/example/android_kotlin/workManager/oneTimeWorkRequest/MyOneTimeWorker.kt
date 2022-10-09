package com.example.android_kotlin.workManager.oneTimeWorkRequest

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyOneTimeWorker(var context : Context, var params : WorkerParameters)
    : Worker(context,params) {
    companion object{
        val ONE_TIME_WORKER = "ONE_TIME_WORKER"
        val PROGRESS = "PROGRESS"
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
}