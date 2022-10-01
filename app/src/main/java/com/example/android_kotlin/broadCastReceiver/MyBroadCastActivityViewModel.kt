package com.example.android_kotlin.broadCastReceiver

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyBroadCastActivityViewModel : ViewModel() {
    val boardCastMsgTv = MutableLiveData<String>()
    val sendLocalBroadCastBtn = MutableLiveData<Boolean>()
    val sendGlobalBroadCastBtn = MutableLiveData<Boolean>()

    fun sendLocalBroadCast(view : View){
        sendLocalBroadCastBtn.value = true
    }

    fun sendGlobalBroadCast(view: View){
        sendGlobalBroadCastBtn.value = true
    }
}