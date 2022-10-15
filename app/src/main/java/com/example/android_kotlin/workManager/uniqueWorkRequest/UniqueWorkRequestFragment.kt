package com.example.android_kotlin.workManager.uniqueWorkRequest

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
import androidx.work.*
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.WmFragmentUniqueWorkRequestBinding
import java.util.concurrent.TimeUnit

class UniqueWorkRequestFragment : Fragment() {
    companion object{
        val PROGRESS = "PROGRESS"
        var WORK_COUNT = 0
        val UNIQUE_WORK_BROAD_CAST = "UNIQUE_WORK_BROAD_CAST"

        val WORK_TYPE = "WORK_TYPE"
        val WORK_TYPE_REPLACE = "WORK_TYPE_REPLACE"
        val WORK_TYPE_KEEP = "WORK_TYPE_KEEP"
        val WORK_TYPE_APPEND = "WORK_TYPE_APPEND"
        val WORK_TYPE_APPEND_OR_REPLACE = "WORK_TYPE_APPEND_OR_REPLACE"

    }
    lateinit var fragmentVm : UniqueWorkRequestFragmentVM;
    lateinit var mBinding: WmFragmentUniqueWorkRequestBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentVm = ViewModelProvider(this)[UniqueWorkRequestFragmentVM::class.java]
        mBinding = DataBindingUtil.inflate(inflater,R.layout.wm_fragment_unique_work_request,container,false)
        mBinding.lifecycleOwner = this
        mBinding.fragmentVm = fragmentVm

        val intentFilter = IntentFilter(UNIQUE_WORK_BROAD_CAST)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,intentFilter)

        fragmentVm.replacePolicyClick.observe(viewLifecycleOwner, Observer{
            WORK_COUNT++

            val data = Data.Builder().putInt(WORK_TYPE_REPLACE, WORK_COUNT).build()
            val periodicWorkRequest = PeriodicWorkRequestBuilder<MyUniqueWorkReplace>(
                15, TimeUnit.MINUTES,
                14, TimeUnit.MINUTES)
                .setInputData(data)
                .build()
            WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(WORK_TYPE_REPLACE,
                ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
        })

        fragmentVm.keepPolicyClick.observe(viewLifecycleOwner, Observer{
            WORK_COUNT++

            val data = Data.Builder().putInt(WORK_TYPE_KEEP, WORK_COUNT).build()
            val constraints = Constraints.Builder().setRequiresCharging(true).build()
            val workRequest = OneTimeWorkRequestBuilder<MyUniqueWorkKeep>()
                .setInputData(data)
                .setConstraints(constraints)
                .build()
            WorkManager.getInstance(requireContext()).enqueueUniqueWork(WORK_TYPE_KEEP,
                ExistingWorkPolicy.KEEP,workRequest)
        })

        fragmentVm.appendPolicyClick.observe(viewLifecycleOwner, Observer{
            WORK_COUNT++
            val data = Data.Builder().putInt(WORK_TYPE_APPEND, WORK_COUNT).build()
            val constraints = Constraints.Builder().setRequiresCharging(true).build()
            val workRequest = OneTimeWorkRequestBuilder<MyUniqueWorkAppend>()
                .setInputData(data)
                .setConstraints(constraints)
                .build()
            WorkManager.getInstance(requireContext()).enqueueUniqueWork(WORK_TYPE_APPEND,
                ExistingWorkPolicy.APPEND,workRequest)
        })

        fragmentVm.appendOrReplacePolicyClick.observe(viewLifecycleOwner, Observer{
            WORK_COUNT++
            val data = Data.Builder().putInt(WORK_TYPE_APPEND_OR_REPLACE, WORK_COUNT).build()
            val constraints = Constraints.Builder().setRequiresCharging(true).build()
            val workRequest = OneTimeWorkRequestBuilder<MyUniqueWorkAppendOrReplace>()
                .setInputData(data)
                .setConstraints(constraints)
                .build()
            WorkManager.getInstance(requireContext()).enqueueUniqueWork(WORK_TYPE_APPEND_OR_REPLACE,
                ExistingWorkPolicy.APPEND_OR_REPLACE,workRequest)
        })
        return mBinding.root
    }

    val receiver : BroadcastReceiver = object :  BroadcastReceiver(){
        override fun onReceive(ctx: Context?, intent: Intent?) {
            val workType = intent?.extras?.get(WORK_TYPE) as String
            val workUpdate = intent.extras?.get(PROGRESS) as String
            when(workType){
                WORK_TYPE_REPLACE -> {fragmentVm.replacePolicyTv.value = workUpdate}
                WORK_TYPE_KEEP -> {fragmentVm.keepPolicyTv.value = workUpdate}
                WORK_TYPE_APPEND -> {fragmentVm.appendPolicyTv.value = workUpdate}
                WORK_TYPE_APPEND_OR_REPLACE -> {fragmentVm.appendOrReplacePolicyTv.value = workUpdate}
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }
}