package com.example.android_kotlin.services

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityServiceExampleBinding
import com.example.android_kotlin.services.boundService.BoundServiceFragment
import com.example.android_kotlin.services.foreGroundService.ForegroundServiceFragment
import com.example.android_kotlin.services.intentService.MyIntentServiceFragment
import com.example.android_kotlin.services.jobIntentService.MyJobIntentService
import com.example.android_kotlin.services.jobIntentService.MyJobIntentServiceFragment
import com.example.android_kotlin.services.startedService.StartedServiceFragment

class ServiceExampleActivity : AppCompatActivity() {
    companion object{
        val STARTED_SERVICE = "STARTED_SERVICE"
        val BOUND_SERVICE = "BOUND_SERVICE"
        val FOREGROUND_SERVICE = "FOREGROUND_SERVICE"
        val INTENT_SERVICE = "INTENT_SERVICE"
        val JOB_INTENT_SERVICE = "JOB_INTENT_SERVICE"

    }
    override fun onCreate(bundle : Bundle?){
        super.onCreate(bundle)

        val activityVm = ViewModelProvider(this)[ServiceExampleViewModel::class.java]
        val mBinding = DataBindingUtil.setContentView<ActivityServiceExampleBinding>(this, R.layout.activity_service_example)
        mBinding.lifecycleOwner = this
        mBinding.activityVm = activityVm

        activityVm.startServiceBtn.observe(this, Observer {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = StartedServiceFragment()
            fragmentTransaction.add(mBinding.fragmentContainer.id, fragment, STARTED_SERVICE)
            fragmentTransaction.addToBackStack(STARTED_SERVICE)
            fragmentTransaction.commit()
        })
        activityVm.boundServiceBtn.observe(this, Observer {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = BoundServiceFragment()
            fragmentTransaction.add(mBinding.fragmentContainer.id, fragment, BOUND_SERVICE)
            fragmentTransaction.addToBackStack(STARTED_SERVICE)
            fragmentTransaction.commit()
        })
        activityVm.foregroundServiceBtn.observe(this, Observer {
            val  fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ForegroundServiceFragment()
            fragmentTransaction.add(mBinding.fragmentContainer.id,fragment, FOREGROUND_SERVICE)
            fragmentTransaction.addToBackStack(STARTED_SERVICE)
            fragmentTransaction.commit()
        })
        activityVm.intentServiceBtn.observe(this, Observer {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = MyIntentServiceFragment()
            fragmentTransaction.add(mBinding.fragmentContainer.id,fragment, INTENT_SERVICE)
            fragmentTransaction.addToBackStack(INTENT_SERVICE)
            fragmentTransaction.commit()
        })
        activityVm.jobIntentServiceBtn.observe(this, Observer{
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = MyJobIntentServiceFragment()
            fragmentTransaction.add(mBinding.fragmentContainer.id, fragment)
            fragmentTransaction.addToBackStack(JOB_INTENT_SERVICE)
            fragmentTransaction.commit()
        })
    }
}