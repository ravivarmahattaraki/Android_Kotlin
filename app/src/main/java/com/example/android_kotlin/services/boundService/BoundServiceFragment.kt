package com.example.android_kotlin.services.boundService

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.FragmentBoundServiceBinding

class BoundServiceFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val fragmentVm = ViewModelProvider(this)[BoundServiceFragmentViewModel::class.java]
        val mBinding = DataBindingUtil.inflate<FragmentBoundServiceBinding>(inflater, R.layout.fragment_bound_service,container,false)
        mBinding.lifecycleOwner = this
        mBinding.fragmentVm = fragmentVm

        return mBinding.root
    }
}