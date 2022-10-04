package com.example.android_kotlin.dataBinding

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingExampleViewModel : ViewModel() {
    companion object{
        val U_NAME = "RAVI"
        val PASSWORD = "RAVI"
    }
    val textViewLiveData = MutableLiveData<String>()
    val textViewVisibility = MutableLiveData<Boolean>()
    val loginBtnClk = MutableLiveData<Boolean>()

    val errorTVLiveData = MutableLiveData<String>()
    val errorTVVisibility = MutableLiveData<Boolean>()

    val dummy = MutableLiveData<String>()

    public fun login(view : View){
        loginBtnClk.setValue(true)
    }
}