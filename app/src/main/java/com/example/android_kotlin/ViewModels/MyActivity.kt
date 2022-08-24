package com.example.android_kotlin.ViewModels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.android_kotlin.R

class MyActivity : AppCompatActivity() {

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
        var viewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        openActivity = findViewById(R.id.button1)

        textView.setText("${viewModel.number}")
        button.setOnClickListener(View.OnClickListener{
            viewModel.addOne()
            textView.text = "${viewModel.number}"
        })
        openActivity.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MyActivityTwo::class.java)
            startActivity(intent)
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