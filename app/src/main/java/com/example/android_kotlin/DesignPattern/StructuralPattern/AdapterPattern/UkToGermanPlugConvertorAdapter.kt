package com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern

import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.German.GermanPlug
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.Uk.UkPlug


/** Adapter converts adaptee to target*/
class UkToGermanPlugConvertorAdapter : UkPlug {
    lateinit var germanPlug: GermanPlug
    constructor(germanPlug: GermanPlug){
        this.germanPlug = germanPlug
    }

    override fun provideElectricity() : String {
        return germanPlug.provideElectricity()
    }
}