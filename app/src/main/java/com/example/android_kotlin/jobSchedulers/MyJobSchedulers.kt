package com.example.android_kotlin.jobSchedulers

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/** JOB SCHEDULERS */
class MyJobSchedulers : JobService() {
    companion object{
        var COUNT = 0
        var WORK_DONE_COUNT = 0
        val JOB_ID = 123
        val JOB_SCHEDULER = "JOB_SCHEDULER"
        val PROGRESS_KEY = "PROGRESS_KEY"
    }
    override fun onStartJob(jobParam: JobParameters?): Boolean {
        COUNT ++
        var intent = Intent(JOB_SCHEDULER)
        intent.putExtra(PROGRESS_KEY, "job started $COUNT")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        Thread(Runnable {
            for (i in 1..10){
                Thread.sleep(1000)
                intent = Intent(JOB_SCHEDULER)
                intent.putExtra(PROGRESS_KEY, "job progress ${i*10}% complete")
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }

            Thread.sleep(1000)
            Handler(mainLooper).post(Runnable {
                WORK_DONE_COUNT ++
                intent = Intent(JOB_SCHEDULER)
                intent.putExtra(PROGRESS_KEY, "job done ${WORK_DONE_COUNT}")
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                jobFinished(jobParam, true)
            })
        }).start()
        return true
    }
    override fun onStopJob(p0: JobParameters?): Boolean { return true }
}