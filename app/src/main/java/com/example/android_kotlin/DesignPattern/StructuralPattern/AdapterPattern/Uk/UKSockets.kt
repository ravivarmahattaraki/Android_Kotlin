package com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.Uk

class UKSockets : UkPlug {
    override fun provideElectricity() : String{
        return "UK Electricity"
    }
}