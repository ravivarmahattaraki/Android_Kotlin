package com.example.android_kotlin.services.boundService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Toast

/** Bound Service */
class MyBoundService : Service(){

    inner class MyBinder : Binder(){
        fun getService() : MyBoundService{
            /** provide object of service class to clients to access properties*/
            return this@MyBoundService
        }
    }
    fun getTimeStamp() : String{
        /** This method will be called by client*/
        val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
        val hours = (elapsedMillis / 3600000)
        val minutes = (elapsedMillis - hours * 3600000) / 60000
        val seconds = (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000
        val millis = (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000)

        return "$hours : $minutes : $seconds : $millis"
    }
    private val iBinder = MyBinder()
    private lateinit var chronometer : Chronometer

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(applicationContext, "onCreate()", Toast.LENGTH_SHORT).show()
        chronometer = Chronometer(this)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    override fun onBind(p0: Intent?): IBinder? {
        /** bind service will be call only once on client is bound to service, */
        Toast.makeText(applicationContext, "onBind()", Toast.LENGTH_SHORT).show()
        return iBinder
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Toast.makeText(applicationContext, "onRebind()", Toast.LENGTH_SHORT).show()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        super.onUnbind(intent)
        Toast.makeText(applicationContext, "onUnbind()", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometer.stop()
        Toast.makeText(applicationContext, "onDestroy()", Toast.LENGTH_SHORT).show()
    }


}