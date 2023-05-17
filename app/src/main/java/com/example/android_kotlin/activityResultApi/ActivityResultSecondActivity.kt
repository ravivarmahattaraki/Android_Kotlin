package com.example.android_kotlin.activityResultApi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityResultApiSecondActivityBinding

class ActivityResultSecondActivity : AppCompatActivity() {
    lateinit var activityVM: ActivityResultSecondActivityVM
    lateinit var activityBinding: ActivityResultApiSecondActivityBinding
    companion object{
        const val HELLO_MESSAGE = "HELLO_MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityVM = ViewModelProvider(this)[ActivityResultSecondActivityVM::class.java]
        activityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_result_api_second_activity)
        activityBinding.lifecycleOwner = this
        activityBinding.activityVm = activityVM

        activityVM.navigateBack.observe(this) { boolean ->
            val intent = Intent().apply {
                putExtra(HELLO_MESSAGE,"Hi from second Activity")
            }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_OK)
        finish()
    }
}