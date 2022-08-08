package com.example.android_kotlin.Activities.ConfigChanges

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.R

class ConfigChangeActivity : AppCompatActivity(), View.OnClickListener{
    private val tag = "LIFE_CYCLE_FIRST_ACT"
    private val COUNTER_KEY = "COUNTER_KEY"
    private var incrementBtn : Button? = null
    private var decrementBtn : Button? = null
    private var enterTextET : EditText? = null
    private var counterTv : TextView? = null
    private var counter = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_change)
        incrementBtn = findViewById(R.id.incrementBtn)
        decrementBtn = findViewById(R.id.decrementBtn)
        enterTextET = findViewById(R.id.enterTextET)
        counterTv = findViewById(R.id.counterTV)

        incrementBtn?.setOnClickListener(this);
        decrementBtn?.setOnClickListener(this)

        val savedCounter = savedInstanceState?.getInt(COUNTER_KEY)
        if(savedCounter != null)
        counter = savedCounter
        counterTv?.setText("$savedCounter")
        Log.d(tag, "onCreate: ")
    }

    override fun onStart() {
        super.onStart()

        Log.d(tag, "onStart: ")
    }

    override fun onResume() {
        super.onResume()

        Log.d(tag, "onResume: ")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(tag, "onRestart: ")
    }


    override fun onPause() {
        super.onPause()

        Log.d(tag, "onPause: ")
    }

    override fun onStop() {
        super.onStop()

        Log.d(tag, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(tag, "onDestroy: ")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(COUNTER_KEY, counter)
        Log.d(tag, "onSaveInstanceState: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        val savedCounter = savedInstanceState?.getInt(COUNTER_KEY)
        counterTv?.setText("$savedCounter");
        Log.d(tag, "onRestoreInstanceState: ")
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.incrementBtn ->{
                counter++
            }
            R.id.decrementBtn ->{
                counter--
            }
        }
        counterTv?.setText("$counter")
    }
}