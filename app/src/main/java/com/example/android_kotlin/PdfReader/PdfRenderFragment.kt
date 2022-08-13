package com.example.android_kotlin.PdfReader

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.window.layout.WindowMetrics
import androidx.window.layout.WindowMetricsCalculator
import com.example.android_kotlin.R
import java.io.FileNotFoundException


class PdfRenderFragment : Fragment() {
    private val mScaleGestureDetector: ScaleGestureDetector? = null
    private val mScaleFactor = 1.0f
    var width : Int = 0
    var height : Int = 0

    lateinit var pdfIv : ImageView
    companion object{
        const val TAG = "PdfRenderFragment"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_pdf_render, container, false)
        pdfIv = view.findViewById(R.id.pdfIV)


        val windowMetrics: WindowMetrics =
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(requireActivity())
        height = windowMetrics.bounds.height()
        width = windowMetrics.bounds.width()


        return view
    }


    override fun onResume() {
        super.onResume()
        /** case 1.
         * java.io.FileNotFoundException: This file can not be opened as a file descriptor;
         * it is probably compressed
         * add in gradle.build aaptOptions { noCompress "pdf"}
         * */
        try {
            val parcelFileDescriptor =
                context?.assets?.openFd("Android_kotlin.pdf")?.parcelFileDescriptor
            val pdfRenderer = PdfRenderer(parcelFileDescriptor!!)
            Log.d(TAG, "${pdfRenderer.pageCount}")
            val pageCount = pdfRenderer.pageCount
            for (i in 0 until pageCount-1){
                val page = pdfRenderer.openPage(0);

                val w: Int = page.width//width//pdfIv.measuredWidth
                val h: Int = page.height//height//pdfIv.measuredHeight

                val conf = Bitmap.Config.ARGB_8888 // see other conf types

                val mBitMap = Bitmap.createBitmap(w, h, conf)

                page.render(mBitMap!!,null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                pdfIv.setImageBitmap(mBitMap)
                page.close()
            }

        }catch (e : FileNotFoundException){
            Log.e(TAG, e.printStackTrace().toString())
        }
    }

}