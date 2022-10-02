package com.example.android_kotlin.services.boundService

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoundServiceFragmentViewModel : ViewModel(){
    val startServiceBtn = MutableLiveData<Boolean>()
    val stopServiceBtm = MutableLiveData<Boolean>()
    val showTimeBtn = MutableLiveData<Boolean>()

    val setTimeTv = MutableLiveData<String>()

    fun startServiceClick(view : View){
        startServiceBtn.value = true
    }

    fun stopServiceClick(view : View){
        stopServiceBtm.value = true
    }

    fun showTimeClick(view: View){
        showTimeBtn.value = true
    }
}