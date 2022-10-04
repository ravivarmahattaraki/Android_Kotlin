package com.example.android_kotlin.services.jobIntentService

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyJobIntentServiceFragmentViewModel : ViewModel() {
    val enqueueWorkBtn = MutableLiveData<Boolean>()
    val progressTV = MutableLiveData<String>()

    fun enqueueWorkBtnClick(view : View){
        enqueueWorkBtn.value = true
    }

}