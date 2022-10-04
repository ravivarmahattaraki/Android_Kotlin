package com.example.android_kotlin.ListView

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.Const
import com.example.android_kotlin.PdfReader.PdfRenderFragment
import com.example.android_kotlin.R

class ListViewActivity : AppCompatActivity() {
    var nameList = arrayOf("Android","JAVA","Kotlin","Activity","BroadCast","Services",
                            "Content provider","Fragment")
    var listView : ListView? = null
    lateinit var pdfIV : ImageView
    var adapter : ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        listView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this,R.layout.item_list_view,nameList)
        listView?.adapter = adapter

        pdfIV = findViewById(R.id.infoListViewIv)
        pdfIV.setOnClickListener(View.OnClickListener {
            startFragment()
        })
    }

    private fun startFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val pdfRenderFragment = PdfRenderFragment()
        val bundle = Bundle()
        bundle.putString(Const.PDF_FILE, "List_view.pdf")
        pdfRenderFragment.arguments = bundle
        fragmentTransaction.add(
            R.id.activityFragmentContainer, pdfRenderFragment,
            "PdfRenderFragment"
        )
        fragmentTransaction.addToBackStack("PdfRenderFragment")
        fragmentTransaction.commit()
    }
}