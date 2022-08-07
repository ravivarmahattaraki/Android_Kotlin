package com.example.android_kotlin.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.R
import java.security.AccessController.getContext

class FirstActivity : AppCompatActivity() {

    private val tag = "LIFE_CYCLE_FIRST_ACT"
    private var navigateBTN : Button? = null;
    override fun onCreate(saveInstanceState : Bundle?){
        super.onCreate(saveInstanceState)

        setContentView(R.layout.activity_first)
        navigateBTN = findViewById(R.id.navigateBTN);
        navigateBTN?.setOnClickListener(View.OnClickListener {
            startActivity()
        })
        val toast = Toast.makeText(this, "onCreate()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onCreate: ")
    }

    override fun onStart(){
        super.onStart()
        val toast = Toast.makeText(this, "onStart()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(this, "onResume()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onResume: ")
    }

    override fun onPause(){
        super.onPause()
        val toast = Toast.makeText(this, "onPause()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onPause: ")
    }

    override fun onStop(){
        super.onStop()
        val toast = Toast.makeText(this, "onStop()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        val toast = Toast.makeText(this, "onDestroy()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        val toast = Toast.makeText(this, "onRestart()",Toast.LENGTH_LONG).show()
        Log.d(tag, "onRestart: ")
    }

    fun startActivity(){
        val bundle : Bundle = Bundle();
        bundle.putString("NAME","Ravivarma")
        val intent : Intent = Intent(this@FirstActivity,SecondActivity::class.java)
        intent.putExtras(bundle);
        startActivity(intent)
    }
}