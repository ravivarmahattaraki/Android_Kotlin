package com.example.android_kotlin.DesignPattern.StructuralPattern.CompositePatterns

class Employee(var name : String, var id : Int, var designation : String) {
    private var list = ArrayList<Employee>()
    fun add(employee: Employee){
        list.add(employee)
    }
    fun remove(employee: Employee){
        list.remove(employee)
    }
    fun getEmployees() : ArrayList<Employee>{
        return list
    }

    override fun toString(): String {
        return "EMPLOYEE : [name = '$name', " +
                "id = '$id', " +
                "designation = '$designation']"
    }
}