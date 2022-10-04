package com.example.android_kotlin.DesignPattern.StructuralPattern.FacadePattern

import com.example.android_kotlin.abstraction.Shape

class ShapeMaker {
    private lateinit var rectangle : IShape
    private lateinit var circle: IShape
    constructor(){
        rectangle = Rectangle()
        circle = Circle()
    }
    fun drawCircle() : String{
        return circle.draw()
    }
    fun drawRectangle() : String{
        return rectangle.draw()
    }
}