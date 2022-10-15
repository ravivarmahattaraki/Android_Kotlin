package com.example.android_kotlin.workManager

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyWorkManagerActivityViewModel: ViewModel() {
    val onTimeWorkReqClick = MutableLiveData<Boolean>()
    val expeditedWorkReqBtnClick = MutableLiveData<Boolean>()
    val periodicWorkReqBtn = MutableLiveData<Boolean>()
    val uniqueWorkReqBtn = MutableLiveData<Boolean>()

    fun onTimeWorkReqClick(view : View){
        onTimeWorkReqClick.value = true
    }

    fun expeditedWorkReqBtnClick(view : View){
        expeditedWorkReqBtnClick.value = true
    }

    fun periodicWorkReqBtnClick(view : View){
        periodicWorkReqBtn.value = true
    }
    fun uniqueWorkReqBtnClick(view : View){
        uniqueWorkReqBtn.value = true
    }
}