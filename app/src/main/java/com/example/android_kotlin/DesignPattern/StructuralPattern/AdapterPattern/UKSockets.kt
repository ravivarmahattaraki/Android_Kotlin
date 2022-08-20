package com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern

class UKSockets : UkPlug{
    override fun provideElectricity() : String{
        return "UK Electricity"
    }
}