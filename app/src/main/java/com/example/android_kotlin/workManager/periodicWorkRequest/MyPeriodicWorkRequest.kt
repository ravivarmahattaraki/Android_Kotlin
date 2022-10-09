package com.example.android_kotlin.workManager.periodicWorkRequest

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android_kotlin.workManager.periodicWorkRequest.PeriodicWorkRequestFragment.Companion.PROGRESS
import com.example.android_kotlin.workManager.periodicWorkRequest.PeriodicWorkRequestFragment.Companion.PROGRESS_RECEIVER
import com.example.android_kotlin.workManager.periodicWorkRequest.PeriodicWorkRequestFragment.Companion.STATE_RECEIVER
import com.example.android_kotlin.workManager.periodicWorkRequest.PeriodicWorkRequestFragment.Companion.WORK_STATE
import com.example.android_kotlin.workManager.periodicWorkRequest.PeriodicWorkRequestFragment.Companion.WORK_STATE_TIME

class MyPeriodicWorkRequest(var context:Context, var params:WorkerParameters) : Worker(context,params) {
    override fun doWork(): Result {

        sendWorkStateBroadCast(WorkState.STARTED.state, System.currentTimeMillis())

        for(i in 1..10){
            Thread.sleep(1000)
            sendProgressBroadCast("Progress ${i*10} % complete")
        }

        sendWorkStateBroadCast(WorkState.FINISHED.state, System.currentTimeMillis())
        return Result.success()
    }

    fun sendProgressBroadCast(message : String){
        val intent = Intent(PROGRESS_RECEIVER)
        intent.putExtra(PROGRESS, message)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
    fun sendWorkStateBroadCast(message : String, time : Long){
        val intent = Intent(STATE_RECEIVER)
        intent.putExtra(WORK_STATE, message)
        intent.putExtra(WORK_STATE_TIME, time)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}