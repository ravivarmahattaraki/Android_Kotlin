package com.example.android_kotlin.services.jobIntentService

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
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.FragmentJobIntentServiceBinding
import com.example.android_kotlin.services.jobIntentService.MyJobIntentService.Companion.JOB_INTENT_SERVICE
import com.example.android_kotlin.services.jobIntentService.MyJobIntentService.Companion.PROGRESS_KEY

class MyJobIntentServiceFragment : Fragment() {

    lateinit var fragmentVm : MyJobIntentServiceFragmentViewModel;
    lateinit var mbinding : FragmentJobIntentServiceBinding
    lateinit var intentFilter : IntentFilter
    lateinit var localBroadcastManager : LocalBroadcastManager
    override fun onCreate(bundle : Bundle?){
        super.onCreate(bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentVm = ViewModelProvider(this)[MyJobIntentServiceFragmentViewModel::class.java]
        mbinding = DataBindingUtil.inflate<FragmentJobIntentServiceBinding>(inflater, R.layout.fragment_job_intent_service,container,false)
        mbinding.lifecycleOwner = this
        mbinding.fragmentVm = fragmentVm

        fragmentVm.enqueueWorkBtn.observe(viewLifecycleOwner, Observer {
            val intent = Intent(context, MyJobIntentService::class.java)
            MyJobIntentService.enQueueMyWork(requireContext(),intent)
        })
        localBroadcastManager = LocalBroadcastManager.getInstance(requireContext())
        intentFilter = IntentFilter(JOB_INTENT_SERVICE)
        localBroadcastManager.registerReceiver(receiver,intentFilter)

        return mbinding.root
    }

    val receiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val progress = intent?.extras?.get(PROGRESS_KEY) as String
            fragmentVm.progressTV.value = progress
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        localBroadcastManager.unregisterReceiver(receiver)
    }
}