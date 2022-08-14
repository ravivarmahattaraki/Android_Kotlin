package com.example.android_kotlin.PdfReader.recyclerView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PointF
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R

class PdfRenderAdapter(val context: Context,var list: ArrayList<Bitmap>) :
    RecyclerView.Adapter<PdfPageViewHolder>(), View.OnTouchListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfPageViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_pdf_page_render,parent,false)
        //view.setOnTouchListener(this)
        return PdfPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PdfPageViewHolder, position: Int) {
        holder.imageView.setImageBitmap(list[position])
        holder.imageView.setOnTouchListener(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    private val TAG = "Touch"
    private val MIN_ZOOM = 1f
    private val MAX_ZOOM = 1f

    // These matrices will be used to scale points of the image
    var matrix: Matrix = Matrix()
    var savedMatrix: Matrix = Matrix()

    // The 3 states (events) which the user is trying to perform
    val NONE = 0
    val DRAG = 1
    val ZOOM = 2
    var mode = NONE

    // these PointF objects are used to record the point(s) the user is touching
    var start = PointF()
    var mid = PointF()
    var oldDist = 1.0

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onTouch(v: View, event: MotionEvent): Boolean {

        val view = v as ImageView
        //view.scaleType = ConstraintLayout.ScaleType.MATRIX
        val scale: Double
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                savedMatrix.set(matrix)
                start.set(event.x, event.y)
                Log.d(TAG, "mode=DRAG") // write to LogCat
                mode = DRAG
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                mode = NONE
                Log.d(TAG, "mode=NONE")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                Log.d(TAG, "oldDist=$oldDist")
                if (oldDist > 5f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                    Log.d(TAG, "mode=ZOOM")
                }
            }
            MotionEvent.ACTION_MOVE -> if (mode == DRAG) {
                matrix.set(savedMatrix)
                matrix.postTranslate(
                    event.x - start.x,
                    event.y - start.y
                ) // create the transformation in the matrix  of points
            } else if (mode == ZOOM) {
                // pinch zooming
                val newDist = spacing(event)
                Log.d(TAG, "newDist=$newDist")
                if (newDist > 5f) {
                    matrix.set(savedMatrix)
                    scale = newDist / oldDist // setting the scaling of the
                    // matrix...if scale > 1 means
                    // zoom in...if scale < 1 means
                    // zoom out
                    matrix.postScale(scale.toFloat(), scale.toFloat(), mid.x, mid.y)
                }
            }
        }
        view.animationMatrix = matrix
        //view.imageMatrix = matrix // display the transformation on screen
        return true // indicate event was handled
    }


    private fun spacing(event: MotionEvent): Double {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt((x * x + y * y).toDouble())
    }

    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point[x / 2] = y / 2
    }
}