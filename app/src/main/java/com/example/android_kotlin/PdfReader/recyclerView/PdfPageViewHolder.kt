package com.example.android_kotlin.PdfReader.recyclerView

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R
import com.example.android_kotlin.RecyclerView.RecyclerViewHolder

class PdfPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView = itemView.findViewById(R.id.item_pdfIV)
    var next : ImageView = itemView.findViewById(R.id.next)
    var previous : ImageView = itemView.findViewById(R.id.previous)
    var pageNumber : TextView = itemView.findViewById(R.id.pageCount)

}