package com.example.android_kotlin.dataBinding

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityDataBindingExampleBinding

class DataBindingExampleActivity : AppCompatActivity() {


    lateinit var dataBinding: ActivityDataBindingExampleBinding
    lateinit var vm : DataBindingExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[DataBindingExampleViewModel::class.java]
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_example)
        /** must set Life cycle owner or
         * else UI never update*/
        dataBinding.lifecycleOwner = this
        dataBinding.dataBindingExampleViewModel = vm


        vm.loginBtnClk.observe(this, Observer {
            if (it){
                val userName = dataBinding.editTextUSerName.text.toString()
                val password = dataBinding.editTextPaswrod.text.toString()
                if(userName.equals(DataBindingExampleViewModel.U_NAME) &&
                    password.equals(DataBindingExampleViewModel.PASSWORD)){
                    vm.textViewLiveData.value = getString(R.string.login_success)
                    vm.textViewVisibility.value = true
                    vm.errorTVVisibility.value = false
                }else{
                    vm.errorTVLiveData.value = getString(R.string.incorrect_username_password)
                    vm.errorTVVisibility.value = true
                    vm.textViewVisibility.value = false
                }
            }
        })


        //vm.loginBtnClk.observe(this, Observer(click))

        vm.textViewLiveData.observe(this, Observer {
            Log.d("DATA_BIN","observe")

        })

    }

    val click : (item : Boolean)->Unit = {
        val userName = dataBinding.editTextUSerName.text.toString()
        val password = dataBinding.editTextPaswrod.text.toString()
        if(userName.equals(DataBindingExampleViewModel.U_NAME) &&
            password.equals(DataBindingExampleViewModel.PASSWORD)){
            vm.textViewLiveData.value = "RAV";//getString(R.string.login_success)
            vm.textViewVisibility.value = true
            vm.errorTVVisibility.value = false
            Log.d("DATA_BIN", vm.textViewLiveData.value!!)
        }else{
            vm.errorTVLiveData.value = "ERR"//getString(R.string.incorrect_username_password)
            vm.errorTVVisibility.value = true
            vm.textViewVisibility.value = false

        }
    }

}