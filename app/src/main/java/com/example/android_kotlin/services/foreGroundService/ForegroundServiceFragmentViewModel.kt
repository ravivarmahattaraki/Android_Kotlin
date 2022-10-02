package com.example.android_kotlin.services.foreGroundService

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForegroundServiceFragmentViewModel : ViewModel() {
    val startServiceBtn = MutableLiveData<Boolean>()
    val stopServiceBtm = MutableLiveData<Boolean>()

    fun startServiceClick(view : View){
        startServiceBtn.value = true
    }

    fun stopServiceClick(view : View){
        stopServiceBtm.value = true
    }
}