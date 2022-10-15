package com.example.android_kotlin.workManager.uniqueWorkRequest

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android_kotlin.workManager.uniqueWorkRequest.UniqueWorkRequestFragment.Companion.PROGRESS
import com.example.android_kotlin.workManager.uniqueWorkRequest.UniqueWorkRequestFragment.Companion.UNIQUE_WORK_BROAD_CAST
import com.example.android_kotlin.workManager.uniqueWorkRequest.UniqueWorkRequestFragment.Companion.WORK_TYPE_APPEND
import com.example.android_kotlin.workManager.uniqueWorkRequest.UniqueWorkRequestFragment.Companion.WORK_TYPE_REPLACE

class MyUniqueWorkAppend(var context : Context, var params : WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        var workNo = params.inputData.getInt(WORK_TYPE_APPEND,-1)
        sendBroadCast(WORK_TYPE_APPEND, "Work ${workNo} Started")
        Thread.sleep(1000)

        for(i in 1..10){
            sendBroadCast(WORK_TYPE_APPEND, "Work ${workNo} progress ${i*10}% completed")
            Thread.sleep(1000)
        }

        sendBroadCast(WORK_TYPE_APPEND, "Work ${workNo} Finished")

        return Result.success()
    }

    fun sendBroadCast(workType : String, message : String){
        val intent = Intent(UNIQUE_WORK_BROAD_CAST)
        intent.putExtra(UniqueWorkRequestFragment.WORK_TYPE, workType)
        intent.putExtra(PROGRESS, message)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}