package com.example.android_kotlin.RecyclerView

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.Const
import com.example.android_kotlin.PdfReader.PdfRenderFragment
import com.example.android_kotlin.R

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var pdfIV: ImageView
    private var list : ArrayList<ProgrammingLanguageData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        list = ArrayList()
        list?.add(ProgrammingLanguageData(R.drawable.android,"Android","Android"))
        list?.add(ProgrammingLanguageData(R.drawable.c,"c","c langauge"))
        list?.add(ProgrammingLanguageData(R.drawable.java,"JAVA","java"))
        list?.add(ProgrammingLanguageData(R.drawable.html,"HTML","html"))
        list?.add(ProgrammingLanguageData(R.drawable.php,"PHP","php"))
        list?.add(ProgrammingLanguageData(R.drawable.mysql,"DATABASE","mySql"))
        list?.add(ProgrammingLanguageData(R.drawable.python,"PYTHON","python"))
        list?.add(ProgrammingLanguageData(R.drawable.swift,"SWIFT","swifts"))

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = RecyclerAdapter(this, list!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        pdfIV = findViewById(R.id.infoRecyclerViewIv)
        pdfIV.setOnClickListener(View.OnClickListener {
            startFragment()
        })

    }
    private fun startFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val pdfRenderFragment = PdfRenderFragment()
        val bundle = Bundle()
        bundle.putString(Const.PDF_FILE, "Recycler_view.pdf")
        pdfRenderFragment.arguments = bundle
        fragmentTransaction.add(
            R.id.activityFragmentContainer, pdfRenderFragment,
            "PdfRenderFragment"
        )
        fragmentTransaction.addToBackStack("PdfRenderFragment")
        fragmentTransaction.commit()
    }
}