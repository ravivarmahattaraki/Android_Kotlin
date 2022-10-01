package com.example.android_kotlin.services.foreGroundService

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.FragmentForegroundServiceBinding
import com.example.android_kotlin.databinding.FragmentStartedServiceBinding

class ForegroundServiceFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val fragmentVm = ViewModelProvider(this)[ForegroundServiceFragmentViewModel::class.java]
        //val mBinding = DataBindingUtil.setContentView<FragmentStartedServiceBinding>(requireActivity(), R.layout.fragment_started_service)
        val mBinding = DataBindingUtil.inflate<FragmentForegroundServiceBinding>(inflater, R.layout.fragment_foreground_service,container, false)
        mBinding.lifecycleOwner = this
        mBinding.fragmentVm = fragmentVm

        return mBinding.root
    }
}