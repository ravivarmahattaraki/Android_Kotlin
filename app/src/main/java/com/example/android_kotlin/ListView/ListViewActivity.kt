package com.example.android_kotlin.ListView

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.R

class ListViewActivity : AppCompatActivity() {
    var nameList = arrayOf("Android","JAVA","Kotlin","Activity","BroadCast","Services",
                            "Content provider","Fragment")
    var listView : ListView? = null
    var adapter : ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        listView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this,R.layout.item_list_view,nameList)
        listView?.adapter = adapter
    }
}