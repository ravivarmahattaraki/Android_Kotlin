package com.example.android_kotlin.DesignPattern.StructuralPattern.FacadePattern

import com.example.android_kotlin.abstraction.Shape

class Rectangle : IShape {
    override fun draw() : String {
        return "Rectangle"
    }
}