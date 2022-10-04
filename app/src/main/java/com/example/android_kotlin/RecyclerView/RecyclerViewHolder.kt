package com.example.android_kotlin.RecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R

class RecyclerViewHolder : RecyclerView.ViewHolder {
    var iconIV : ImageView? = null
    var titleTV : TextView? = null
    var subTitle : TextView? = null
    var item : View? = null
    constructor(item : View) : super(item) {
        iconIV = item.findViewById(R.id.programingLangIv)
        titleTV = item.findViewById(R.id.titleTV)
        subTitle = item.findViewById(R.id.subtitleTV)
        this.item = item
    }
}