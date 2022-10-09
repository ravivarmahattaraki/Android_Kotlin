package com.example.android_kotlin.workManager.periodicWorkRequest

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeriodicWorkRequestFragmentVM : ViewModel() {

    val progressTv = MutableLiveData<String>()
    val startWorkBtn = MutableLiveData<Boolean>()
    val enqueuedTimeTv = MutableLiveData<String>()
    val startedTimeTv = MutableLiveData<String>()
    val finishedTimeTv = MutableLiveData<String>()

    fun startWorkBtnClick(view : View){
        startWorkBtn.value = true
    }
}