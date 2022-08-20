package com.example.android_kotlin.DesignPattern.StructuralPattern

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.*
import com.example.android_kotlin.R

class StructuralPatternActivity : AppCompatActivity() {
    companion object{
        const val ADAPTER_PATTERN = "ADAPTER_PATTERN"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creational_pattern)

        /** Adapter Patterns
         * Client*/
        val germanPlug : GermanPlug = GermanSockets()
        Log.d(ADAPTER_PATTERN, germanPlug.provideElectricity())

        val ukPlug : UkPlug = UKSockets()
        Log.d(ADAPTER_PATTERN, ukPlug.provideElectricity())

        val adapter : UkPlug = UkToGermanPlugConvertorAdapter(germanPlug)
        Log.d(ADAPTER_PATTERN, adapter.provideElectricity())


    }

}