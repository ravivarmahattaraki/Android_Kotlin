package com.example.android_kotlin.MVVM

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_kotlin.MVVM.model.MVVMActivityDataManager
import com.example.android_kotlin.MVVM.model.UserData

class MVVMActivityViewModel : ViewModel(), MVVMActivityContract.ViewModel{

    val createAccountLiveData = MutableLiveData<String>()
    val loginLiveData = MutableLiveData<String>()

    lateinit var dataManager : MVVMActivityDataManager
    override fun init(context: Context) {
        dataManager = MVVMActivityDataManager(context)
        dataManager.init()
    }

    override fun createAccount(userData: UserData) {
        dataManager.createAccount(userData)
        createAccountLiveData.value = "Account created Successfully"
    }

    override fun login(userData: UserData) {
        if(userData.userName.isEmpty() && userData.password.isEmpty()){
            loginLiveData.value = "user name and password cannot be empty"
            return
        }
        val login = dataManager.login()
        if(userData.userName == login.userName && userData.password == login.password){
            loginLiveData.value = "Logged in SuccessFully"
        }else{
            loginLiveData.value = "Incorrect username/password"
        }
    }
}