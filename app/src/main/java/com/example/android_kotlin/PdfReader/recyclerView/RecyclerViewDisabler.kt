package com.example.android_kotlin.PdfReader.recyclerView

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDisabler : RecyclerView.OnItemTouchListener {
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}