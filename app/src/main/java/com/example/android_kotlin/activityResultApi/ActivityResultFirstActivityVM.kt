package com.example.android_kotlin.activityResultApi

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityResultFirstActivityVM : ViewModel() {

    val navigateSecondActivityLiveData = MutableLiveData<Boolean>()
    fun navigateSecondActivity(button : View){
        navigateSecondActivityLiveData.value = true
    }

}