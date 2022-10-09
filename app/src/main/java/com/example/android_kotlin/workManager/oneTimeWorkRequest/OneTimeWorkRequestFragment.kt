package com.example.android_kotlin.workManager.oneTimeWorkRequest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.WmFragmentOneTimeWorkRequestBinding
import com.example.android_kotlin.workManager.oneTimeWorkRequest.MyOneTimeWorker.Companion.ONE_TIME_WORKER
import com.example.android_kotlin.workManager.oneTimeWorkRequest.MyOneTimeWorker.Companion.PROGRESS

class OneTimeWorkRequestFragment : Fragment() {
    lateinit var mFragmentVm: OneTimeWorkRequestFragmentVM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mFragmentVm = ViewModelProvider(this)[OneTimeWorkRequestFragmentVM::class.java]
        val mBinding = DataBindingUtil.inflate<WmFragmentOneTimeWorkRequestBinding>(inflater,
            R.layout.wm_fragment_one_time_work_request,container, false)
        mBinding.lifecycleOwner = this;
        mBinding.fragmentVm = mFragmentVm


        val intentFilter = IntentFilter(ONE_TIME_WORKER)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,intentFilter)

        /** Enqueue one time work request */
        mFragmentVm.startWorkBtn.observe(viewLifecycleOwner, Observer {
            val oneTimeWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<MyOneTimeWorker>().build()
            val workManager = WorkManager.getInstance(requireContext())
            workManager.enqueue(oneTimeWorkRequest)
        })

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    val receiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val progress : String = intent?.extras?.get(PROGRESS) as String
            mFragmentVm.progressTv.value = progress
        }
    }
}