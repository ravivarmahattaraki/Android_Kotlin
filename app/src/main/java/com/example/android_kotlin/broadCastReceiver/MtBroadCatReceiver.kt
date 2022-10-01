package com.example.android_kotlin.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

/** defination of global broadcast */
class MtBroadCatReceiver : BroadcastReceiver() {
    val broadCastReceivedLiveData = MutableLiveData<String>()
    override fun onReceive(p0: Context?, intent: Intent?) {
        Toast.makeText(p0, "global broad cast", Toast.LENGTH_SHORT).show()
        val count = intent?.extras?.get(MyBroadCastActivity.KEY)
        broadCastReceivedLiveData.value = "$count "
    }
}