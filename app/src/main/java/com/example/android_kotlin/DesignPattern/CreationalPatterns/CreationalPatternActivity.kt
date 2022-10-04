package com.example.android_kotlin.DesignPattern.CreationalPatterns

import android.app.Notification
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.Const.Companion.PDF_FILE
import com.example.android_kotlin.DesignPattern.CreationalPatterns.Builder.JavaCarBuilder
import com.example.android_kotlin.DesignPattern.CreationalPatterns.Builder.KotlinCarBuilder
import com.example.android_kotlin.DesignPattern.CreationalPatterns.Factory.Shape
import com.example.android_kotlin.DesignPattern.CreationalPatterns.Factory.ShapeFactory
import com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon.ClassicSingleTon
import com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon.KotlinSingleTon
import com.example.android_kotlin.DesignPattern.CreationalPatterns.SingleTon.ThreadSafeSingleTon
import com.example.android_kotlin.PdfReader.PdfRenderFragment
import com.example.android_kotlin.R
import java.util.concurrent.CountDownLatch

class CreationalPatternActivity : AppCompatActivity() {
    companion object{
        const val CLASSIC_SINGLE_TON = "CLASSIC_SINGLE_TON"
        const val CLASSIC_SINGLE_TON_THREAD = "CLASSIC_SINGLE_TON_THREAD"
        const val BUILDER_PATTERN = "BUILDER_PATTERN"
        const val FACTORY_PATTERN = "FACTORY_PATTERN"

    }
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_creational_pattern)
        imageView = findViewById(R.id.infoActivityIv)
        imageView.setOnClickListener(View.OnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString(PDF_FILE,"creational_design_pattern.pdf")
            fragmentTransaction.replace(R.id.activityFragmentContainer,PdfRenderFragment::class.java,bundle)
            fragmentTransaction.commit()
        })
        /** Single ton patterns
         * */
        //classicSingleTon()
        //classicSingleTonThreads()
        //classicSingleTonThreadSafe()
        //kotlinSingleTon()

        /** Builder patterns
         * */
        javaBuilderExample1()
        kotlinBuilderExample1()

        /** Factory Patterns
         * */
        val shapeFactory = ShapeFactory()
        val shape1 : Shape? = shapeFactory.getShape("CIRCLE")
        val shape2 : Shape? = shapeFactory.getShape("RECTANGLE")
        Log.d(FACTORY_PATTERN, "${shape1?.draw()}")
        Log.d(FACTORY_PATTERN, "${shape2?.draw()}")



    }

    /**Singleton Examples
     * */
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

    /**Builder pattern
     * */
    private fun javaBuilderExample1(){
        val javaCarBuilder = JavaCarBuilder.Builder()
            .setCarModel(1)
            .setBrand("Audi")
            .setColor("Blue").build()
        Log.d(BUILDER_PATTERN, javaCarBuilder.toString())

        val javaCarBuilder2 = JavaCarBuilder.Builder()
            .setCarModel(2)
            .setBrand("Porsche")
            .setColor("RED").build()
        Log.d(BUILDER_PATTERN, javaCarBuilder2.toString())

        val javaCarBuilder3 = JavaCarBuilder.Builder().build()
        Log.d(BUILDER_PATTERN, javaCarBuilder3.toString())
    }
    private fun kotlinBuilderExample1(){
        val kotlinCarBuilder = KotlinCarBuilder.Builder()
            .setCarModel(3)
            .setCarBrand("Maruti")
            .setCarColor("Black").build()
        Log.d(BUILDER_PATTERN, kotlinCarBuilder.toString())

        val kotlinCarBuilder1 = KotlinCarBuilder.Builder()
            .setCarModel(4)
            .setCarBrand("Honda")
            .setCarColor("White").build()
        Log.d(BUILDER_PATTERN, kotlinCarBuilder1.toString())

        val kotlinCarBuilder2 = KotlinCarBuilder.Builder().build()
        Log.d(BUILDER_PATTERN, kotlinCarBuilder2.toString())

        val kotlinCarBuilder3 = KotlinCarBuilder.build()
        Log.d(BUILDER_PATTERN, kotlinCarBuilder3.toString())

        val kotlinCarBuilder4 = KotlinCarBuilder.setCarBrand("Bukati").build()
        Log.d(BUILDER_PATTERN, kotlinCarBuilder4.toString())
    }
}