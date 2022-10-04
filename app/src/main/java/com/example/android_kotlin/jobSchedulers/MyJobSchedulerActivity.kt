package com.example.android_kotlin.jobSchedulers

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityJobSchedulersBinding
import com.example.android_kotlin.jobSchedulers.MyJobSchedulers.Companion.JOB_ID
import com.example.android_kotlin.jobSchedulers.MyJobSchedulers.Companion.JOB_SCHEDULER
import com.example.android_kotlin.jobSchedulers.MyJobSchedulers.Companion.PROGRESS_KEY
import com.example.android_kotlin.services.jobIntentService.MyJobIntentServiceFragmentViewModel

class MyJobSchedulerActivity : AppCompatActivity(){
    lateinit var activityVm : MyJobSchedulerActivityViewModel
    lateinit var mBinding : ActivityJobSchedulersBinding
    override fun onCreate(bundle: Bundle?){
        super.onCreate(bundle)
        activityVm = ViewModelProvider(this)[MyJobSchedulerActivityViewModel::class.java]
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_job_schedulers)
        mBinding.lifecycleOwner = this
        mBinding.activityVm = activityVm

        /** In Activity onCreate() */
        val intentFilter = IntentFilter(JOB_SCHEDULER)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,intentFilter)
        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        activityVm.startJobBtn.observe(this, Observer {
            val componentName = ComponentName(applicationContext, MyJobSchedulers::class.java)
            val jobInfo = JobInfo.Builder(JOB_ID,componentName).build()
            val result = jobScheduler.schedule(jobInfo)

            if(result == JobScheduler.RESULT_SUCCESS){
                Toast.makeText(applicationContext, "Job scheduled", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Job not scheduled", Toast.LENGTH_SHORT).show()
            }
        })

        activityVm.cancelJobBtn.observe(this, Observer { jobScheduler.cancel(JOB_ID) })
    }

    /** Local broad cast in Activity class*/
    private val receiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val progress = p1?.extras?.get(PROGRESS_KEY) as String
            activityVm.updateProgressTv.value = progress
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}