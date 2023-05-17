package com.example.android_kotlin.activityResultApi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityResultApiFirstActivityBinding

class ActivityResultFirstActivity : AppCompatActivity() {
    lateinit var activityVm: ActivityResultFirstActivityVM
    lateinit var activityBinding: ActivityResultApiFirstActivityBinding
    lateinit var context: Context

    companion object {
        const val SECOND_ACTIVITY = "SECOND_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        activityVm.navigateSecondActivityLiveData.observe(this) { boolean ->
            val intent = Intent(this, ActivityResultSecondActivity::class.java).apply {
                putExtra(SECOND_ACTIVITY, "Hello")
            }
            activityLaunchers.launch(intent)
        }
    }

    val activityLaunchers =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { activityResult ->
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    activityResult.data?.let { intent ->
                        val string = intent.getStringExtra(ActivityResultSecondActivity.HELLO_MESSAGE)
                        Toast.makeText(context, "Message : $string", Toast.LENGTH_LONG).show()
                    }
                }
            })


    private fun init() {
        context = this
        activityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_result_api_first_activity)
        activityVm = ViewModelProvider(this)[ActivityResultFirstActivityVM::class.java]

        activityBinding.lifecycleOwner = this
        activityBinding.activityVm = activityVm
    }


}