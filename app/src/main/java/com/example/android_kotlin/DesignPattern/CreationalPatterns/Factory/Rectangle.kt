package com.example.android_kotlin.DesignPattern.CreationalPatterns.Factory

import android.util.Log

class Rectangle : Shape{
    override fun draw() : String{
        return "Rectangle Drawing"
    }
}