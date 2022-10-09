package com.example.android_kotlin.workManager.oneTimeWorkRequest

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneTimeWorkRequestFragmentVM : ViewModel() {

    val progressTv = MutableLiveData<String>()
    val startWorkBtn = MutableLiveData<Boolean>()

    fun startWorkBtnClick(view : View){
        startWorkBtn.value = true
    }
}