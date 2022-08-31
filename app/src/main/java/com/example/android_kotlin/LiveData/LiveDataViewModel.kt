package com.example.android_kotlin.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel() {
    public val mutableLiveData = MutableLiveData<Int>()
    var number = 0;
    fun addOne(){
        number++
        mutableLiveData.value = number
    }
}