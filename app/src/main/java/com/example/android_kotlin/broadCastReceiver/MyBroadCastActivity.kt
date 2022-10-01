package com.example.android_kotlin.broadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityMyBroadCastBinding

class MyBroadCastActivity : AppCompatActivity() {

    companion object{
        var COUNT = 0
        var KEY = "MY_BROAD_CAST"
        val LOCAL_RECEIVER = "LOCAL_RECEIVER"
        val GLOBAL_RECEIVER = "GLOBAL_RECEIVER"

    }

    private  var mBinding: ActivityMyBroadCastBinding? = null
    private lateinit var activityVm: MyBroadCastActivityViewModel

    val globalReceiver = MtBroadCatReceiver()
    lateinit var localBroadcastManager : LocalBroadcastManager

    override fun onCreate(bundle : Bundle?){
        super.onCreate(bundle)

        activityVm = ViewModelProvider(this)[MyBroadCastActivityViewModel::class.java]
        mBinding = DataBindingUtil.setContentView<ActivityMyBroadCastBinding>(this, R.layout.activity_my_broad_cast);
        mBinding?.lifecycleOwner = this
        mBinding?.activityVM = activityVm


        /** register local broadcast and global bradcast
         * must pass the string arguments in intent filter
         * if not passed broad cast will not be sent*/
        val localIntentFilter = IntentFilter(LOCAL_RECEIVER)
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.registerReceiver(localReceiver, localIntentFilter)

        //global broadcast
        val globalIntentFilter = IntentFilter(GLOBAL_RECEIVER)
        registerReceiver(globalReceiver,globalIntentFilter)

        /**send local and global broadcast
         * string argument in the intent must be same as registered*/
        activityVm.sendLocalBroadCastBtn.observe(this, Observer {
            COUNT++
            val intent = Intent(LOCAL_RECEIVER)
            intent.putExtra(KEY, COUNT)
            localBroadcastManager.sendBroadcast(intent)
        })

        //send global broadcast
        activityVm.sendGlobalBroadCastBtn.observe(this, Observer {
            COUNT++
            val intent = Intent(GLOBAL_RECEIVER)
            intent.putExtra(KEY, COUNT)
            sendBroadcast(intent)
        })


        globalReceiver.broadCastReceivedLiveData.observe(this, Observer {
            activityVm.boardCastMsgTv.value = it
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(localReceiver)
        unregisterReceiver(globalReceiver)
    }

    /** Defining of local braodcast */
    val localReceiver : BroadcastReceiver = object  : BroadcastReceiver(){
        val broadCastReceivedLiveData = MutableLiveData<String>()
        override fun onReceive(p0: Context?, intent: Intent?) {
            Toast.makeText(applicationContext, "local broad cast", Toast.LENGTH_SHORT).show()
            val count = intent?.extras?.get(MyBroadCastActivity.KEY)
            activityVm.boardCastMsgTv.value = "$count"
        }
    }
}