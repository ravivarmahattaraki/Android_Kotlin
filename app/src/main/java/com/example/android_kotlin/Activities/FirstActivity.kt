package com.example.android_kotlin.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.android_kotlin.Activities.ConfigChanges.ConfigChangeActivity
import com.example.android_kotlin.Const
import com.example.android_kotlin.PdfReader.PdfRenderFragment
import com.example.android_kotlin.PdfReader.PdfRenderManager
import com.example.android_kotlin.R
import java.security.AccessController.getContext

class FirstActivity : AppCompatActivity() {

    private val tag = "LIFE_CYCLE_FIRST_ACT"
    private var navigateBTN : Button? = null;
    private lateinit var configChangeBtn : Button
    private lateinit var infoIv : ImageView
    private lateinit var fragment : FragmentContainerView

    override fun onCreate(saveInstanceState : Bundle?){
        super.onCreate(saveInstanceState)

        setContentView(R.layout.activity_first)
        navigateBTN = findViewById(R.id.navigateBTN);
        configChangeBtn = findViewById(R.id.navigateConfigChangeButton)
        infoIv = findViewById(R.id.infoActivityIv)
        fragment = findViewById(R.id.activityFragmentContainer)
        fragment.bringToFront()
        navigateBTN?.setOnClickListener(View.OnClickListener {
            startActivity()
        })
        configChangeBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ConfigChangeActivity::class.java)
            startActivity(intent)
        })
        infoIv.setOnClickListener(View.OnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val pdfRenderFragment = PdfRenderFragment()
            val bundle = Bundle()
            bundle.putString(Const.PDF_FILE,"Activity.pdf")
            pdfRenderFragment.arguments = bundle
            fragmentTransaction.add(R.id.activityFragmentContainer,pdfRenderFragment,
                "PdfRenderFragment")
            fragmentTransaction.addToBackStack("PdfRenderFragment")
            fragmentTransaction.commit()
        })
        val toast = Toast.makeText(this, "onCreate()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onCreate: ")
    }

    override fun onStart(){
        super.onStart()
        val toast = Toast.makeText(this, "onStart()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(this, "onResume()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onResume: ")
    }

    override fun onPause(){
        super.onPause()
        val toast = Toast.makeText(this, "onPause()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onPause: ")
    }

    override fun onStop(){
        super.onStop()
        val toast = Toast.makeText(this, "onStop()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        val toast = Toast.makeText(this, "onDestroy()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        val toast = Toast.makeText(this, "onRestart()",Toast.LENGTH_SHORT).show()
        Log.d(tag, "onRestart: ")
    }

    fun startActivity(){
        val bundle : Bundle = Bundle();
        bundle.putString("NAME","Ravivarma")
        val intent : Intent = Intent(this@FirstActivity,SecondActivity::class.java)
        intent.putExtras(bundle);
        startActivity(intent)
    }
}