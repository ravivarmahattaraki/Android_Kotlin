package com.example.android_kotlin.MVVM.model

import android.content.Context
import com.example.android_kotlin.MVVM.MVVMActivityContract
class MVVMActivityDataManager(var context: Context) : MVVMActivityContract.DataManager{

    var mMVVMActivityPersistence: MVVMActivityPersistence = MVVMActivityPersistence(context)
    var mMVVMActivityRoomDB: MVVMActivityRoomDB = MVVMActivityRoomDB()
    override fun init() {
        mMVVMActivityPersistence.init()
    }

    override fun createAccount(userData: UserData) {
        mMVVMActivityPersistence.createAccount(userData)
    }

    override fun login() : UserData {
       return mMVVMActivityPersistence.login()
    }
}