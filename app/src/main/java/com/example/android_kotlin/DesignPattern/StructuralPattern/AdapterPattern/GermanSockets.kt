package com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern

class GermanSockets : GermanPlug{
    override fun provideElectricity() : String{
        return "German Elctricity"
    }
}