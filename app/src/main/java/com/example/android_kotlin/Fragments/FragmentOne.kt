package com.example.android_kotlin.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.android_kotlin.Const
import com.example.android_kotlin.PdfReader.PdfRenderFragment
import com.example.android_kotlin.R

class FragmentOne : Fragment() {

    lateinit var navigateBtn : Button
    lateinit var pdfIV : ImageView
    companion object {
        //static block
        private const val TAG = "FRAGMENT_ONE"
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
        val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
        Log.d(TAG, "onCreateView: ")
        navigateBtn = view.findViewById(R.id.openFragmentTwo)
        navigateBtn.setOnClickListener(View.OnClickListener {
            val fragmentManager : FragmentManager? = activity?.supportFragmentManager
            val fragmentTransaction : FragmentTransaction? = fragmentManager?.beginTransaction()
            val fragmentTwo = FragmentTwo()
            fragmentTransaction?.replace(R.id.fragmentContainer,fragmentTwo,"fragment_two")
            fragmentTransaction?.addToBackStack("fragment_one_to_two_transaction")
            fragmentTransaction?.commit()
            Toast.makeText(context,"REPLACE TRANSACTION:\nFragment one replace by fragment two", Toast.LENGTH_LONG).show()

        })

        pdfIV = view.findViewById(R.id.infoFragmentIv)
        pdfIV.setOnClickListener(View.OnClickListener {
            startFragment()
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
    private fun startFragment() {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val pdfRenderFragment = PdfRenderFragment()
        val bundle = Bundle()
        bundle.putString(Const.PDF_FILE, "Fragments.pdf")
        pdfRenderFragment.arguments = bundle
        fragmentTransaction?.add(
            R.id.fragmentContainer, pdfRenderFragment,
            "PdfRenderFragment"
        )
        fragmentTransaction?.addToBackStack("PdfRenderFragment")
        fragmentTransaction?.commit()
    }

}
