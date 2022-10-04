package com.example.android_kotlin.MVVM

import android.content.Context
import com.example.android_kotlin.MVVM.model.UserData

interface MVVMActivityContract {
    interface ViewModel{
        fun init(context: Context)
        fun createAccount(userData: UserData)
        fun login(userData: UserData)
    }

    interface DataManager{
        fun init()
        fun createAccount(userData: UserData)
        fun login() : UserData
    }

    interface Persistence{
        fun init()
        fun createAccount(userData: UserData)
        fun login() : UserData
    }

    interface RoomDB{

    }
}