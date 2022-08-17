package com.example.android_kotlin.PdfReader

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.Const
import com.example.android_kotlin.R

class PdfReaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_render)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val pdfRenderFragment = PdfRenderFragment()
        val bundle = Bundle()
        bundle.putString(Const.PDF_FILE,"Android_kotlin.pdf")
        pdfRenderFragment.arguments = bundle
        fragmentTransaction.replace(
            R.id.fragmentContainerPdfRender, pdfRenderFragment,
            "pdfRenderFragment"
        )
        fragmentTransaction.commit()


    }

}