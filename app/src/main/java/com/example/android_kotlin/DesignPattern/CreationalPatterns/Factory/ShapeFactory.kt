package com.example.android_kotlin.DesignPattern.CreationalPatterns.Factory

class ShapeFactory() {
    public fun getShape(shape : String) : Shape? {
        when(shape){
            "RECTANGLE" -> { return Rectangle() }
            "CIRCLE" -> { return Circle() }
        }
        return null
    }
}