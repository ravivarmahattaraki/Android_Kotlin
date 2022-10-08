package com.example.android_kotlin.workManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityWorkManagerBinding
import com.example.android_kotlin.workManager.oneTimeWorkRequest.OneTimeWorkRequestFragment

class MyWorkManagerActivity : AppCompatActivity() {
    lateinit var mActivityVm : MyWorkManagerActivityViewModel
    lateinit var mBinding : ActivityWorkManagerBinding
    override fun onCreate(bundle : Bundle?){
        super.onCreate(bundle)
        mActivityVm = ViewModelProvider(this)[MyWorkManagerActivityViewModel::class.java]
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager)
        mBinding.lifecycleOwner = this
        mBinding.activityVm = mActivityVm

        mActivityVm.onTimeWorkReqClick.observe(this, Observer {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = OneTimeWorkRequestFragment()
            fragmentTransaction.add(mBinding.fragmentContainer.id, fragment,"OneTimeWorkRequestFragment")
            fragmentTransaction.addToBackStack("OneTimeWorkRequestFragment")
            fragmentTransaction.commit()
        })

    }
}