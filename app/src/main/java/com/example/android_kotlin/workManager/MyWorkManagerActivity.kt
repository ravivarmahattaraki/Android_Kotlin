package com.example.android_kotlin.workManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityWorkManagerBinding
import com.example.android_kotlin.workManager.expeditedWorkRequest.ExpeditedWorkRequestFragment
import com.example.android_kotlin.workManager.oneTimeWorkRequest.OneTimeWorkRequestFragment
import com.example.android_kotlin.workManager.periodicWorkRequest.PeriodicWorkRequestFragment

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
            openFragment("OneTimeWorkRequestFragment", OneTimeWorkRequestFragment())
        })

        mActivityVm.expeditedWorkReqBtnClick.observe(this, Observer{
            openFragment("ExpeditedWorkRequestFragment", ExpeditedWorkRequestFragment())
        })

        mActivityVm.periodicWorkReqBtn.observe(this, Observer{
            openFragment("PeriodicWorkRequest", PeriodicWorkRequestFragment())
        })

    }

    fun openFragment(tag : String, fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(mBinding.fragmentContainer.id, fragment,tag)
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }
}