package com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.German

class GermanSockets : GermanPlug {
    override fun provideElectricity() : String{
        return "German Elctricity"
    }
}