package com.example.android_kotlin.PdfReader.recyclerView

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R
import com.example.android_kotlin.RecyclerView.RecyclerViewHolder

class PdfPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView = itemView.findViewById(R.id.item_pdfIV)
}