package com.example.android_kotlin.ViewModels

import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {
    var number = 0;
    fun addOne(){
        number++
    }
}