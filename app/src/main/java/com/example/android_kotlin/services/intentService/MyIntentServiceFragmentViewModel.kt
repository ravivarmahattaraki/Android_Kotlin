package com.example.android_kotlin.services.intentService

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyIntentServiceFragmentViewModel : ViewModel() {
    val serviceUpdateTV = MutableLiveData<String>()
    val startServiceClick = MutableLiveData<Boolean>()
    val stopServiceClick = MutableLiveData<Boolean>()

    fun startServiceClick(view : View){
        startServiceClick.value = true
    }

    fun stoServiceClick(view : View){
        stopServiceClick.value = true
    }
}