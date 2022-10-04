package com.example.android_kotlin.services.intentService

import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.logging.Handler

/** Intent Service*/
class MyIntentService : IntentService("MyIntentService"){
    companion object{
        var COUNT = 0;
        val INTENT_SERVICE = "INTENT_SERVICE"
        val COUNT_KEY = "COUNT_KEY"
        val PROGRESS_KEY = "PROGRESS_KEY"
    }
    override fun onCreate() { super.onCreate() }

    override fun onStart(intent: Intent?, startId: Int) { super.onStart(intent, startId) }

    override fun onHandleIntent(intent: Intent?) {

        val handler = android.os.Handler(mainLooper)
        handler.post(Runnable {
            Toast.makeText(applicationContext, "onHandleIntent", Toast.LENGTH_SHORT).show()
        })

        COUNT ++
        var intent = Intent(INTENT_SERVICE)
        intent.putExtra(COUNT_KEY, COUNT)
        intent.putExtra(PROGRESS_KEY, "In progress")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        Thread.sleep(10000)

        intent = Intent(INTENT_SERVICE)
        intent.putExtra(COUNT_KEY, COUNT)
        intent.putExtra(PROGRESS_KEY, "Work Done")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    override fun onDestroy() { super.onDestroy() }
}