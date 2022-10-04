package com.example.android_kotlin.MVVM

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    private lateinit var application: Application
    
    constructor(application: Application){
        this.application = application
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelFactory(application) as T
    }
}