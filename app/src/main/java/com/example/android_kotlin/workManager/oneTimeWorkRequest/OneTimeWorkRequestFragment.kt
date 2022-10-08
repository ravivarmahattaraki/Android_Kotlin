package com.example.android_kotlin.workManager.oneTimeWorkRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.WmFragmentOneTimeWorkRequestBinding

class OneTimeWorkRequestFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val mFragmentVm = ViewModelProvider(this)[OneTimeWorkRequestFragmentVM::class.java]
        val mBinding = DataBindingUtil.inflate<WmFragmentOneTimeWorkRequestBinding>(inflater,
            R.layout.wm_fragment_one_time_work_request,container, false)
        mBinding.lifecycleOwner = this;
        mBinding.fragmentVm = mFragmentVm

        return mBinding.root
    }
}