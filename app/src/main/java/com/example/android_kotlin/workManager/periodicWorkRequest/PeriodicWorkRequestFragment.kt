package com.example.android_kotlin.workManager.periodicWorkRequest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.WmFragmentPeriodicWorkRequestBinding
import com.example.android_kotlin.workManager.periodicWorkRequest.MyPeriodicWorkRequest.Companion.WORK_TIME
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class PeriodicWorkRequestFragment : Fragment() {
    companion object{
        val TAG = "PERIODIC_WORK"
        val PROGRESS_RECEIVER = "PROGRESS_RECEIVER"
        val PROGRESS = "PROGRESS"

        val STATE_RECEIVER = "STATE_RECEIVER"
        val WORK_STATE = "WORK_STATE"
        val WORK_STATE_TIME = "WORK_STATE_TIME"

    }
    lateinit var mFragmentVm: PeriodicWorkRequestFragmentVM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mFragmentVm = ViewModelProvider(this)[PeriodicWorkRequestFragmentVM::class.java]
        val mBinding = DataBindingUtil.inflate<WmFragmentPeriodicWorkRequestBinding>(inflater,
            R.layout.wm_fragment_periodic_work_request,container, false)
        mBinding.lifecycleOwner = this;
        mBinding.fragmentVm = mFragmentVm


        val intentFilter = IntentFilter(PROGRESS_RECEIVER)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,intentFilter)

        val intentFilter2 = IntentFilter(STATE_RECEIVER)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(workStateReceiver,intentFilter2)

        /** Enqueue Periodic work request
         *  below work will run for 5 minutes
         *  of last 15 minutes of interval*/
        mFragmentVm.startWorkBtn.observe(viewLifecycleOwner, Observer {
            sendWorkStateBroadCast(WorkState.ENQUEUED.state,System.currentTimeMillis())
            val repeatInterval = mBinding.repeatIntervalET.text.toString().toLong()
            val flexInterval = mBinding.flexIntervalET.text.toString().toLong()
            WORK_TIME = mBinding.workTimET.text.toString().toInt()
            val periodicWorkRequest = PeriodicWorkRequestBuilder<MyPeriodicWorkRequest>(
                repeatInterval, TimeUnit.MINUTES,
                flexInterval, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(requireContext()).enqueue(periodicWorkRequest)
        })

        mFragmentVm.cancelWorkBtn.observe(viewLifecycleOwner, Observer {
            mFragmentVm.progressTv.value = "All work cancelled"
            mFragmentVm.startedTimeTv.value = ""
            mFragmentVm.enqueuedTimeTv.value = ""
            mFragmentVm.finishedTimeTv.value = ""

            WorkManager.getInstance(requireContext()).cancelAllWork()
        })

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(workStateReceiver)
    }

    val receiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val progress : String = intent?.extras?.get(PROGRESS) as String
            mFragmentVm.progressTv.value = progress
        }
    }

    val workStateReceiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(contex: Context?, intent: Intent?) {
            val state : String = intent?.extras?.get(WORK_STATE) as String
            val time : Long = intent?.extras?.get(WORK_STATE_TIME) as Long

            when(state){
                WorkState.ENQUEUED.state ->{
                    mFragmentVm.enqueuedTimeTv.value = "Work Enqueued : ${getDate(time)}"
                }
                WorkState.STARTED.state ->{
                    mFragmentVm.startedTimeTv.value = "Work Started : ${getDate(time)}"
                }
                WorkState.FINISHED.state ->{
                    mFragmentVm.finishedTimeTv.value = "Work Finished : ${getDate(time)}"
                }
            }
        }

    }

    fun getDate(milliSeconds: Long): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds)
        return formatter.format(calendar.getTime())
    }

    fun sendWorkStateBroadCast(message : String, time : Long){
        val intent = Intent(STATE_RECEIVER)
        intent.putExtra(WORK_STATE, message)
        intent.putExtra(WORK_STATE_TIME, time)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }
}