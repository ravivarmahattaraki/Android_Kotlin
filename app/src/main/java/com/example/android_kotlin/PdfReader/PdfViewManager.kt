package com.example.android_kotlin.PdfReader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.FileNotFoundException

class PdfRenderManager {

    private lateinit var fileName: String
    private lateinit var context: Context
    private lateinit var parcelFileDescriptor: ParcelFileDescriptor
    private lateinit var pdfRenderer: PdfRenderer
    var list: ArrayList<Bitmap> = ArrayList()
    companion object{
        const val TAG = "PdfRenderManager"
    }
    public constructor(fileName : String, context : Context){
        this.fileName = fileName
        this.context = context
        init()
    }

    private fun init() {
        this.parcelFileDescriptor = getFileDescriptor()
        this.pdfRenderer = getPdfRenderer()
        createBitmaps()
    }

    private fun getFileDescriptor() : ParcelFileDescriptor {
        return context.assets.openFd(fileName).parcelFileDescriptor
    }

    @JvmName("getPdfRenderer1")
    private fun getPdfRenderer() : PdfRenderer{
        return PdfRenderer(parcelFileDescriptor)
    }
    /** case 1.
     * java.io.FileNotFoundException: This file can not be opened as a file descriptor;
     * it is probably compressed
     * add in gradle.build aaptOptions { noCompress "pdf"}
     * */
    private fun createBitmaps() {
        try {
            for (i in 0 until pdfRenderer.pageCount-1){
                val page = pdfRenderer.openPage(i);
                val w: Int = page.width*2  //width//pdfIv.measuredWidth
                val h: Int = page.height*2//height//pdfIv.measuredHeight
                val conf = Bitmap.Config.ARGB_8888 // see other conf types
                val mBitMap = Bitmap.createBitmap(w, h, conf)
                page.render(mBitMap!!,null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                list.add(mBitMap)
                page.close()
            }
        }catch (e : FileNotFoundException){
            Log.e(TAG, e.printStackTrace().toString())
        }
    }

    public fun getBitMapList() : ArrayList<Bitmap>{
        return list
    }

    public fun close (){
        parcelFileDescriptor.close()
        pdfRenderer.close()
        list.clear()
    }
}