package com.example.android_kotlin.services.boundService

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.FragmentBoundServiceBinding

class BoundServiceFragment : Fragment() {

    var myBoundService : MyBoundService? = null
    private val serviceConnection = object : ServiceConnection{
        /** Service connection provides object of Service through iBinder*/
        override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?) {
            val myBinder = binder as MyBoundService.MyBinder
            myBoundService = myBinder.getService()
        }

        /** this method will be called if service stops unexpectedly by system*/
        override fun onServiceDisconnected(p0: ComponentName?) { myBoundService = null }

        override fun onBindingDied(name: ComponentName?) { super.onBindingDied(name) }

        override fun onNullBinding(name: ComponentName?) { super.onNullBinding(name) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val fragmentVm = ViewModelProvider(this)[BoundServiceFragmentViewModel::class.java]
        val mBinding = DataBindingUtil.inflate<FragmentBoundServiceBinding>(inflater, R.layout.fragment_bound_service,container,false)
        mBinding.lifecycleOwner = this
        mBinding.fragmentVm = fragmentVm




        fragmentVm.startServiceBtn.observe(viewLifecycleOwner, Observer {
            val intent = Intent(context, MyBoundService::class.java)
            context?.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        })

        fragmentVm.stopServiceBtm.observe(viewLifecycleOwner, Observer {
            context?.unbindService(serviceConnection)
            myBoundService = null
        })

        fragmentVm.showTimeBtn.observe(viewLifecycleOwner, Observer {
            if(myBoundService != null){
                fragmentVm.setTimeTv.value = myBoundService?.getTimeStamp()
            }else{
                fragmentVm.setTimeTv.value = "Service is disconnected"
            }
        })


        return mBinding.root
    }


}