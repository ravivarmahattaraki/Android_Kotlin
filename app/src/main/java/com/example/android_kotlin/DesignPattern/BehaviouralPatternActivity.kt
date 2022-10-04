package com.example.android_kotlin.DesignPattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.android_kotlin.Const
import com.example.android_kotlin.PdfReader.PdfRenderFragment
import com.example.android_kotlin.R

class BehaviouralPatternActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavioural_pattern)
        imageView = findViewById(R.id.infoActivityIv)
        imageView.setOnClickListener(View.OnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString(Const.PDF_FILE,"behavioural_pattern.pdf")
            fragmentTransaction.replace(R.id.activityFragmentContainer,
                PdfRenderFragment::class.java,bundle)
            fragmentTransaction.commit()
        })
    }
}