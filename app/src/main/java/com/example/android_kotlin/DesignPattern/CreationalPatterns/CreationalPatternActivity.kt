package com.example.android_kotlin.DesignPattern.CreationalPatterns

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon.ClassicSingleTon
import com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon.KotlinSingleTon
import com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon.ThreadSafeSingleTon
import com.example.android_kotlin.R
import java.util.concurrent.CountDownLatch

class CreationalPatternActivity : AppCompatActivity() {
    companion object{
        const val CLASSIC_SINGLE_TON = "CLASSIC_SINGLE_TON"
        const val CLASSIC_SINGLE_TON_THREAD = "CLASSIC_SINGLE_TON_THREAD"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_creational_pattern)

        /** Single ton patterns
         * */
        //classicSingleTon()
        //classicSingleTonThreads()
        //classicSingleTonThreadSafe()
        kotlinSingleTon()
    }

    private fun classicSingleTon() {
        ClassicSingleTon.destroyObject()
        for (i in 0..2) {
            val instance = ClassicSingleTon.getInstance()
            Log.d(CLASSIC_SINGLE_TON, "object: $i ${instance.hashCode()}")
        }
    }
    private fun kotlinSingleTon() {
        for (i in 0..2) {
            val instance = KotlinSingleTon.hashCode()
            Log.d(CLASSIC_SINGLE_TON, "object: $i ${instance}")
        }
    }
    private fun classicSingleTonThreads(){
        ClassicSingleTon.destroyObject()
        val latch = CountDownLatch(1)
        for (i in 0..0){

            val thread = MyThread(latch)
            val thread1 = MyThread(latch)


            val t1 = Thread(thread)
            t1.name = "thread1"
            val t2 = Thread(thread1)
            t2.name = "thread2"

            t1.start()
            t2.start()
            latch.countDown()

        }
    }
    private fun classicSingleTonThreadSafe(){
        ClassicSingleTon.destroyObject()
        val latch = CountDownLatch(1)
        for (i in 0..0){

            val thread = MyThread2(latch)
            val thread1 = MyThread2(latch)


            val t1 = Thread(thread)
            t1.name = "thread1"
            val t2 = Thread(thread1)
            t2.name = "thread2"

            t1.start()
            t2.start()
            latch.countDown()

        }
    }


    internal class MyThread(latch: CountDownLatch) : Runnable {
        var latch: CountDownLatch

        override fun run() {
            try {
                latch.await() //The thread keeps waiting till it is informed
                val instance = ClassicSingleTon.getInstance()
                Log.d(CLASSIC_SINGLE_TON_THREAD, "time : ${System.currentTimeMillis()} ${instance.hashCode()}")

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            //Do the actual thing
        }

        init {
            this.latch = latch
        }
    }
    internal class MyThread2(latch: CountDownLatch) : Runnable {
        var latch: CountDownLatch
        override fun run() {
                latch.await() //The thread keeps waiting till it is informed
                val instance = ThreadSafeSingleTon.getInstance()
                Log.d(CLASSIC_SINGLE_TON_THREAD, "thread safe time : " +
                        "${System.currentTimeMillis()} ${instance.hashCode()}")
        }
        init {
            this.latch = latch
        }
    }
}