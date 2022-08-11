package com.example.android_kotlin.Fragments

import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.android_kotlin.R

class FragmentActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentOne: FragmentOne
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentOne = FragmentOne()
        fragmentTransaction.add(R.id.fragmentContainer,fragmentOne,"fragment_one")
        fragmentTransaction.commit()
    }
}