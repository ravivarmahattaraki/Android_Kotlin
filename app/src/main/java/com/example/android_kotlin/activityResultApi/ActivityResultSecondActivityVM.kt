package com.example.android_kotlin.activityResultApi

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityResultSecondActivityVM : ViewModel() {
    val navigateBack = MutableLiveData<Boolean>()

    fun navigateBackClick(view : View){
        navigateBack.value = true
    }
}