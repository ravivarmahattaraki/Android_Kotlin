package com.example.android_kotlin.jobSchedulers

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyJobSchedulerActivityViewModel : ViewModel() {
    val startJobBtn = MutableLiveData<Boolean>()
    val cancelJobBtn = MutableLiveData<Boolean>()
    val updateProgressTv = MutableLiveData<String>()

    fun startBtnClick(view : View){
        startJobBtn.value = true
    }

    fun cancelBtnClick(view : View){
        cancelJobBtn.value = true
    }
}