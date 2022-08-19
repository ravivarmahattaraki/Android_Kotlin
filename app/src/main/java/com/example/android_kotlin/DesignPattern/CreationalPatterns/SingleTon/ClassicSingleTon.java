package com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon;

/** Classic singleton are not thread safe*/
public class ClassicSingleTon {
    private static ClassicSingleTon obj =null;

    private ClassicSingleTon(){}

    public static ClassicSingleTon getInstance() {
        if(obj == null){
            obj = new ClassicSingleTon();
        }
        return obj;
    }
    public static void destroyObject(){
        obj = null;
    }
}
