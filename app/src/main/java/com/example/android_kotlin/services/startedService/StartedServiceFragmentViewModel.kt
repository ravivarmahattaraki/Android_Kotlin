package com.example.android_kotlin.services.startedService

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartedServiceFragmentViewModel : ViewModel() {
    val startServiceBtn = MutableLiveData<Boolean>()
    val stopServiceBtm = MutableLiveData<Boolean>()

    fun startServiceClick(view : View){
        startServiceBtn.value = true
    }

    fun stopServiceClick(view : View){
        startServiceBtn.value = true
    }
}