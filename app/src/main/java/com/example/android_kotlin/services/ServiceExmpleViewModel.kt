package com.example.android_kotlin.services

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServiceExampleViewModel : ViewModel() {
    val startServiceBtn = MutableLiveData<Boolean>()
    val foregroundServiceBtn = MutableLiveData<Boolean>()
    val boundServiceBtn = MutableLiveData<Boolean>()

    fun startedServiceClick(view : View){
        startServiceBtn.value = true
    }

    fun foreGroundServiceClick(view : View){
        foregroundServiceBtn.value = true
    }

    fun boundServiceClick(view : View){
        boundServiceBtn.value = true
    }

}