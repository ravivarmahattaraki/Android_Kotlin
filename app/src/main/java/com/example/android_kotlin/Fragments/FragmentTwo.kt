package com.example.android_kotlin.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.android_kotlin.R

class FragmentTwo : Fragment() {

    lateinit var backBtn : Button
    companion object {
        //static block
        private const val TAG = "FRAGMENT_Two"
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_two, container, false)
        Log.d(TAG, "onCreateView: ")
        backBtn = view.findViewById(R.id.back)
        backBtn.setOnClickListener(View.OnClickListener {
            val fragmentManager : FragmentManager? = activity?.supportFragmentManager
            val fragmentTransaction : FragmentTransaction? = fragmentManager?.beginTransaction()
            val fragmentOne = FragmentOne()
            fragmentTransaction?.add(R.id.fragmentContainer,fragmentOne,"fragment_two")
            fragmentTransaction?.addToBackStack("fragment_two_to_one_transaction")
            fragmentTransaction?.commit()
            Toast.makeText(context,"ADD TRANSACTION:\nFragment one is overlapped on fragment two", Toast.LENGTH_LONG).show()
        })
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }


}
