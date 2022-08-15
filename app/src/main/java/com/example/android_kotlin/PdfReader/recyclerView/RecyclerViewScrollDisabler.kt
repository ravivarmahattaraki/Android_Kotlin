package com.example.android_kotlin.PdfReader.recyclerView

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollDisabler : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }
}