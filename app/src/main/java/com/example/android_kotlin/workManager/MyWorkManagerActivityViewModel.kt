package com.example.android_kotlin.workManager

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyWorkManagerActivityViewModel: ViewModel() {
    val onTimeWorkReqClick = MutableLiveData<Boolean>()

    fun onTimeWorkReqClick(view : View){
        onTimeWorkReqClick.value = true
    }
}