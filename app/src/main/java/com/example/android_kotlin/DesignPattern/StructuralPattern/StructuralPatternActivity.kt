package com.example.android_kotlin.DesignPattern.StructuralPattern

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.DesignPattern.CreationalPatterns.Factory.Shape
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.*
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.German.GermanPlug
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.German.GermanSockets
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.Uk.UKSockets
import com.example.android_kotlin.DesignPattern.StructuralPattern.AdapterPattern.Uk.UkPlug
import com.example.android_kotlin.DesignPattern.StructuralPattern.CompositePatterns.Employee
import com.example.android_kotlin.DesignPattern.StructuralPattern.FacadePattern.ShapeMaker
import com.example.android_kotlin.R

class StructuralPatternActivity : AppCompatActivity() {
    companion object{
        const val ADAPTER_PATTERN = "ADAPTER_PATTERN"
        const val FACADE_PATTERN = "FACADE_PATTERN"
        const val COMPOSITE_PATTERN = "COMPOSITE_PATTERN"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creational_pattern)

        /** Adapter Patterns
         * Client*/
        adapterPattern()

        /**Facade Patters
        * Client*/
        FacadePattern()

        /** Composite Patterns
         * client*/
        val ceo = Employee("Ravi",1,"CEO")
        val manager1 = Employee("Rupa", 2, "Manager")
        val manager2 = Employee("CV", 3, "Manager")
        ceo.add(manager1)
        ceo.add(manager2)

        val supervisor = Employee("Manju", 4, "Supervisor")
        manager1.add(supervisor)

        val softwareEngg1 = Employee("Gautem", 5, "Software Engineer")
        val softwareEngg2 = Employee("Priya", 6, "Software Engineer")
        supervisor.add(softwareEngg1)
        supervisor.add(softwareEngg2)

        Log.d(COMPOSITE_PATTERN, ceo.toString())
        for (managers in ceo.getEmployees()){
            Log.d(COMPOSITE_PATTERN, managers.toString())
            for(sup in managers.getEmployees()){
                Log.d(COMPOSITE_PATTERN, sup.toString())
                for (sw in sup.getEmployees()){
                    Log.d(COMPOSITE_PATTERN, sw.toString())
                }
            }
        }
    }

    fun compositePattern(){
        val ceo = Employee("Ravi",1,"CEO")
        val manager1 = Employee("Rupa", 2, "Manager")
        val manager2 = Employee("CV", 3, "Manager")
        ceo.add(manager1)
        ceo.add(manager2)

        val supervisor = Employee("Manju", 4, "Supervisor")
        manager1.add(supervisor)

        val softwareEngg1 = Employee("Gautem", 5, "Software Engineer")
        val softwareEngg2 = Employee("Priya", 6, "Software Engineer")
        supervisor.add(softwareEngg1)
        supervisor.add(softwareEngg2)


        Log.d(COMPOSITE_PATTERN, ceo.toString())
        for (managers in ceo.getEmployees()){
            Log.d(COMPOSITE_PATTERN, managers.toString())
            for(sup in managers.getEmployees()){
                Log.d(COMPOSITE_PATTERN, sup.toString())
                for (sw in sup.getEmployees()){
                    Log.d(COMPOSITE_PATTERN, sw.toString())
                }
            }
        }
    }
    fun adapterPattern(){
        val germanPlug : GermanPlug = GermanSockets()
        Log.d(ADAPTER_PATTERN, germanPlug.provideElectricity())

        val ukPlug : UkPlug = UKSockets()
        Log.d(ADAPTER_PATTERN, ukPlug.provideElectricity())

        val adapter : UkPlug = UkToGermanPlugConvertorAdapter(germanPlug)
        Log.d(ADAPTER_PATTERN, adapter.provideElectricity())
    }
    fun FacadePattern(){
        val shapeMaker = ShapeMaker()
        Log.d(FACADE_PATTERN, shapeMaker.drawRectangle())
        Log.d(FACADE_PATTERN, shapeMaker.drawCircle())
    }
}