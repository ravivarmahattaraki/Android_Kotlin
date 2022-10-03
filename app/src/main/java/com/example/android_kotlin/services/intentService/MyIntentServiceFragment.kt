package com.example.android_kotlin.services.intentService

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
import com.example.android_kotlin.databinding.FragmentIntentServiceBinding
import com.example.android_kotlin.services.intentService.MyIntentService.Companion.COUNT_KEY
import com.example.android_kotlin.services.intentService.MyIntentService.Companion.INTENT_SERVICE
import com.example.android_kotlin.services.intentService.MyIntentService.Companion.PROGRESS_KEY

class MyIntentServiceFragment : Fragment(){
    lateinit var fragmentVm : MyIntentServiceFragmentViewModel
    lateinit var mBinding : FragmentIntentServiceBinding
    lateinit var intentFilter: IntentFilter
    lateinit var broadcastManager: LocalBroadcastManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        fragmentVm = ViewModelProvider(this)[MyIntentServiceFragmentViewModel::class.java]
        mBinding = DataBindingUtil.inflate<FragmentIntentServiceBinding>(inflater, R.layout.fragment_intent_service,
            container, false)
        mBinding.lifecycleOwner = this
        mBinding.fragmentVM = fragmentVm

        /** Register Broadcast for communication with service */
        broadcastManager = LocalBroadcastManager.getInstance(requireContext())
        intentFilter = IntentFilter(INTENT_SERVICE)
        broadcastManager.registerReceiver(broadCastReceiver, intentFilter)

        /** Start or Stop the service on button click*/
        val intent = Intent(context, MyIntentService::class.java)
        fragmentVm.startServiceClick.observe(viewLifecycleOwner, Observer {
            requireActivity().startService(intent)
        })
        fragmentVm.stopServiceClick.observe(viewLifecycleOwner, Observer {
            requireActivity().stopService(intent)
        })

        return mBinding.root
    }

    /** Create local Broad cast in fragment or activity*/
    val broadCastReceiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val count = intent?.extras?.get(COUNT_KEY)
            val progress = intent?.extras?.get(PROGRESS_KEY)
            fragmentVm.serviceUpdateTV.value = "Task $count is $progress"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        broadcastManager.unregisterReceiver(broadCastReceiver)
    }
}