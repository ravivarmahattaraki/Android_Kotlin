package com.example.android_kotlin.PdfReader.recyclerView

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PointF
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import androidx.viewpager2.widget.ViewPager2
import com.example.android_kotlin.R

class PdfRenderAdapter(
    val context: Context,
    var list: ArrayList<Bitmap>,
    var viewPager2: ViewPager2
) :
    RecyclerView.Adapter<PdfPageViewHolder>(), View.OnTouchListener, View.OnClickListener,
    TextWatcher {
    var mRecyclerView: RecyclerView? = null
    lateinit var disabler: RecyclerViewScrollDisabler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfPageViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_pdf_page_render, parent, false)
        //view.setOnTouchListener(this)
        return PdfPageViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView!!)
        this.mRecyclerView = recyclerView
        //mRecyclerView?.setOnTouchListener(this)
        disabler = RecyclerViewScrollDisabler()
        //viewPager2.setPageTransformer(DepthPageTransformer())

    }

    lateinit var holder: PdfPageViewHolder

    override fun onBindViewHolder(holder: PdfPageViewHolder, position: Int) {
        this.holder = holder
        holder.imageView.setImageBitmap(list[position])
        holder.imageView.scaleType = ImageView.ScaleType.MATRIX
        holder.imageView.setOnTouchListener(this)

        holder.pageNumber.setText("${position + 1}")
        holder.pageNumber.setOnClickListener(this)
        //holder.pageNumber.addTextChangedListener(this)

        holder.next.setOnClickListener(this)
        holder.previous.setOnClickListener(this)
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
        return touchEventv1(v, event)
    }

    private fun touchEventv1(v: View, event: MotionEvent): Boolean {
        viewPager2.isUserInputEnabled = false
        val view = v as ImageView
        //view.scaleType = ImageView.ScaleType.MATRIX
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
                //mRecyclerView?.suppressLayout(true)
                oldDist = spacing(event)
                Log.d(TAG, "oldDist=$oldDist")
                if (oldDist > 5f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                    Log.d(TAG, "mode=ZOOM")
                }
                //mRecyclerView?.suppressLayout(false)
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


        //mRecyclerView?.suppressLayout(false)

        view.imageMatrix = matrix // display the transformation on screen
        return true // indicate event was handled
    }

    private fun touchEventv2(v: View, event: MotionEvent): Boolean {

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
                //mRecyclerView?.suppressLayout(true)
                oldDist = spacing(event)
                Log.d(TAG, "oldDist=$oldDist")
                if (oldDist > 5f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                    Log.d(TAG, "mode=ZOOM")
                }
                //mRecyclerView?.suppressLayout(false)
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


        //mRecyclerView?.suppressLayout(false)

        v.matrix.set(matrix)  // display the transformation on screen
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

    override fun onClick(p0: View?) {
        matrix.reset()
        savedMatrix.reset()// = Matrix()
        mode = NONE
        start = PointF()
        mid = PointF()
        oldDist = 1.0

        val currentItem = viewPager2.currentItem
        val holder =
            mRecyclerView?.findViewHolderForAdapterPosition(currentItem) as PdfPageViewHolder
        holder.pageNumber.setText("${currentItem + 1}")
        holder.imageView.imageMatrix = matrix
        //viewPager2.adapter?.notifyDataSetChanged()
        when (p0?.id) {
            R.id.next -> {
                viewPager2.setCurrentItem(currentItem + 1)
            }
            R.id.previous -> {
                viewPager2.setCurrentItem(currentItem - 1)
            }
            R.id.pageCount -> {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setTitle(context.getString(R.string.enter_page_number))
                val editText = EditText(context)
                editText.inputType = InputType.TYPE_CLASS_NUMBER
                alertDialog.setView(editText)

                alertDialog.setPositiveButton(context.getString(R.string.Ok),
                    DialogInterface.OnClickListener
                    { dialogInterface, i ->
                        if (!editText.text.toString().isEmpty()) {
                            val number: Int = editText.text.toString().toInt()
                            viewPager2.setCurrentItem(number-1)
                        }
                    })

                alertDialog.setNegativeButton(context.getString(R.string.close),
                    DialogInterface.OnClickListener
                    { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })

                alertDialog.show()
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (p0.toString().isEmpty()) {
            return
        }
        val number: Int = p0.toString().toInt()
        matrix.reset()
        savedMatrix.reset()// = Matrix()
        mode = NONE
        start = PointF()
        mid = PointF()
        oldDist = 1.0

        val currentItem = viewPager2.currentItem
        val holder =
            mRecyclerView?.findViewHolderForAdapterPosition(currentItem) as PdfPageViewHolder?
        holder?.imageView?.imageMatrix = matrix
        viewPager2.setCurrentItem(number)
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}