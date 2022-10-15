package com.example.android_kotlin.workManager.uniqueWorkRequest

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_kotlin.workManager.uniqueWorkRequest.UniqueWorkRequestFragment.Companion.WORK_COUNT

class UniqueWorkRequestFragmentVM : ViewModel() {
    val replacePolicyClick = MutableLiveData<Boolean>()
    val replacePolicyTv = MutableLiveData<String>("This is periodic work with 15 min and 14 mins of fles")

    val keepPolicyClick = MutableLiveData<Boolean>()
    val keepPolicyTv = MutableLiveData<String>("One time work request with charging true constraint")

    val appendPolicyClick = MutableLiveData<Boolean>()
    val appendPolicyTv = MutableLiveData<String>("One time work request with charging true constraint")

    val appendOrReplacePolicyClick = MutableLiveData<Boolean>()
    val appendOrReplacePolicyTv = MutableLiveData<String>("One time work request with charging true constraint")
    fun replacePolicyClick(view : View){
        replacePolicyClick.value = true
    }

    fun keepPolicyClick(view : View){
        keepPolicyClick.value = true
    }

    fun appendPolicyClick(view : View){
        appendPolicyClick.value = true
    }
    fun appendOrReplacePolicyClick(view : View){
        appendOrReplacePolicyClick.value = true
    }
    fun resetWorkCount(view : View){
        WORK_COUNT = 0
    }

}