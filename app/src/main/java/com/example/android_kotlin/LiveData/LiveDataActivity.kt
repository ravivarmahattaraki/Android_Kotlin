package com.example.android_kotlin.LiveData

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R

class LiveDataActivity : AppCompatActivity() {

    companion object{
        const val TAG = "VIEW_MODEL"
    }
    lateinit var textView : TextView
    lateinit var button : Button
    lateinit var openActivity: Button
    override fun onCreate(bundle : Bundle?){
        super.onCreate(bundle)
        setContentView(R.layout.activity_view_model)
        Log.d(TAG, "onCreate: ")
        var viewModel = ViewModelProvider(this)[LiveDataViewModel::class.java]

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        openActivity = findViewById(R.id.button1)

        textView.setText("${viewModel.number}")
        button.setOnClickListener(View.OnClickListener{
            viewModel.addOne()
        })
        openActivity.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LiveDataActivityTwo::class.java)
            startActivity(intent)
        })
        viewModel.mutableLiveData.observe(this, Observer{
            textView.text = "$it"
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume: ")
    }
    
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }
}