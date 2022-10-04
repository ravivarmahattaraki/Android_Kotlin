package com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon;

/** Classic singleton are not thread safe*/
public class ThreadSafeSingleTon {
    private static ThreadSafeSingleTon obj =null;

    private ThreadSafeSingleTon(){}

    public static synchronized ThreadSafeSingleTon getInstance() {
        if(obj == null){
            obj = new ThreadSafeSingleTon();
        }
        return obj;
    }
    public static void destroyObject(){
        obj = null;
    }
}
