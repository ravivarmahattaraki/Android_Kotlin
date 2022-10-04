package com.example.android_kotlin.MVVM

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.MVVM.model.UserData
import com.example.android_kotlin.R

class MVVMActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var loginBtn : Button
    lateinit var createAccountBtn : Button
    lateinit var userNameET : EditText
    lateinit var pwdEt : EditText
    lateinit var textView: TextView
    lateinit var mMVVMActivityViewModel : MVVMActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        init()

        setClickListeners()
    }

    private fun setClickListeners() {
        loginBtn.setOnClickListener(this)
        createAccountBtn.setOnClickListener(this)
    }

    private fun init() {
        mMVVMActivityViewModel = ViewModelProvider(this)[MVVMActivityViewModel::class.java]
        mMVVMActivityViewModel.init(applicationContext)
        loginBtn = findViewById(R.id.button1)
        createAccountBtn = findViewById(R.id.button2)
        userNameET = findViewById(R.id.editText1)
        pwdEt = findViewById(R.id.editText2)
        textView = findViewById(R.id.textView)

        mMVVMActivityViewModel.loginLiveData.observe(this, Observer {
            textView.text = it
        })
        mMVVMActivityViewModel.createAccountLiveData.observe(this, Observer {
            textView.text = it
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button1 ->{
                val userData = buildUserData()
                mMVVMActivityViewModel.login(userData)
            }
            R.id.button2 ->{
                val userData = buildUserData()
                mMVVMActivityViewModel.createAccount(userData)
            }
        }
    }

    private fun buildUserData(): UserData {
        val uName = userNameET.text.toString()
        var pwd = pwdEt.text.toString()
        val userData = UserData(uName, pwd)
        return userData
    }
}