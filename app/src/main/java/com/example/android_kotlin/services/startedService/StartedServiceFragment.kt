package com.example.android_kotlin.services.startedService

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.FragmentStartedServiceBinding

class StartedServiceFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentVm = ViewModelProvider(this)[StartedServiceFragmentViewModel::class.java]
        //val mBinding = DataBindingUtil.setContentView<FragmentStartedServiceBinding>(requireActivity(), R.layout.fragment_started_service)
        val mBinding = DataBindingUtil.inflate<FragmentStartedServiceBinding>(inflater, R.layout.fragment_started_service,container, false)
        mBinding.lifecycleOwner = this
        mBinding.fragmentVm = fragmentVm

        val intent = Intent(context, MyStartedService::class.java)
        fragmentVm.startServiceBtn.observe(viewLifecycleOwner, Observer {
            requireActivity().startService(intent)
        })

        fragmentVm.stopServiceBtm.observe(viewLifecycleOwner, Observer {
            requireActivity().stopService(intent)
        })

        return mBinding.root
    }
}