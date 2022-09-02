package com.example.android_kotlin.MVVM.model

import android.content.Context
import android.content.SharedPreferences
import com.example.android_kotlin.MVVM.MVVMActivityContract
import com.google.gson.Gson

class MVVMActivityPersistence(var context: Context) : MVVMActivityContract.Persistence{

    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences

    companion object{
        const val PREFERENCES = "com.example.android_kotlin"
        const val LOGIN_DATA = "LOGIN_DATA"
    }

    override fun init() {
        sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    override fun createAccount(userData: UserData) {
        val toJson = Gson().toJson(userData)
        editor.putString(LOGIN_DATA,toJson).apply()
    }

    override fun login() : UserData{
        val string = sharedPreferences.getString(LOGIN_DATA, "")
        if(string != null && !string.isEmpty()){
            val fromJson : UserData = Gson().fromJson(string, UserData::class.java)
            return fromJson
        }
        return UserData("","")
    }
}