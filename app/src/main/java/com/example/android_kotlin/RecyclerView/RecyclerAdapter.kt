package com.example.android_kotlin.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R

class RecyclerAdapter : RecyclerView.Adapter<RecyclerViewHolder> {
    var context : Context ?= null
    var list : List<ProgrammingLanguageData>? = null
    constructor(context: Context, list: List<ProgrammingLanguageData>){
        this.context = context
        this.list = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_recycler_view,parent,false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val programmingLanguageData = list?.get(position)

        holder.iconIV?.setImageResource(programmingLanguageData!!.icon)
        holder.titleTV?.setText(programmingLanguageData!!.title)
        holder.subTitle?.setText(programmingLanguageData!!.subTitle)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

}