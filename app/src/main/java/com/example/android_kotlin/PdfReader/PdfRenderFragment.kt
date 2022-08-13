package com.example.android_kotlin.PdfReader

import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.android_kotlin.R


class PdfRenderFragment : Fragment(), View.OnTouchListener{
    lateinit var pdfRenderManager: PdfRenderManager
    lateinit var pdfIv : ImageView
    lateinit var zoomInBtm : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var scaleGestureDetector: ScaleGestureDetector? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_pdf_render, container, false)
        pdfIv = view.findViewById(R.id.pdfIV)
        pdfRenderManager = PdfRenderManager("Android_kotlin.pdf",requireContext())
        pdfIv.setImageBitmap(pdfRenderManager.list[0])

        pdfIv.setOnTouchListener(this)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pdfIv.setImageBitmap(null)
        pdfRenderManager.close()
    }


    override fun onResume() {
        super.onResume()
    }
    fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        scaleGestureDetector?.onTouchEvent(motionEvent)
        return true
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

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val view = v as ImageView
        view.scaleType = ImageView.ScaleType.MATRIX
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
        view.imageMatrix = matrix // display the transformation on screen
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