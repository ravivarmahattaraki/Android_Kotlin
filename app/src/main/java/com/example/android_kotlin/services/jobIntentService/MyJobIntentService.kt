package com.example.android_kotlin.services.jobIntentService

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.core.app.JobIntentService
import androidx.localbroadcastmanager.content.LocalBroadcastManager
/** Job Intent Service Example */
class MyJobIntentService : JobIntentService() {
    companion object{
        val JOB_ID = 1
        var WORK_COUNT = 0;
        val JOB_INTENT_SERVICE = "JOB_INTENT_SERVICE"
        val PROGRESS_KEY = "PROGRESS_KEY"
        fun enQueueMyWork(context: Context, intent : Intent) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, intent)
        }
    }
    override fun onCreate() { super.onCreate() }

    override fun onHandleWork(intent: Intent) {
        WORK_COUNT++
        val handler = Handler(mainLooper)
        handler.post(Runnable {
            Toast.makeText(applicationContext, "onHandleWork", Toast.LENGTH_SHORT).show()
        })

        var intent = Intent(JOB_INTENT_SERVICE)
        intent.putExtra(PROGRESS_KEY, "Work ${WORK_COUNT} start")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        for(i in 1..10){
            Thread.sleep(1000)
            val intent = Intent(JOB_INTENT_SERVICE)
            intent.putExtra(PROGRESS_KEY, "Work ${WORK_COUNT} Progress ${i*10} % complete")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }

        intent = Intent(JOB_INTENT_SERVICE)
        intent.putExtra(PROGRESS_KEY, "Work ${WORK_COUNT} done")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        Thread.sleep(2000)
    }

    override fun onDestroy() { super.onDestroy() }
}