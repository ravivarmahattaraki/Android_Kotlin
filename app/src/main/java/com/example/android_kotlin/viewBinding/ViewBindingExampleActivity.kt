package com.example.android_kotlin.viewBinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_kotlin.databinding.ActivityViewBindingExampleBinding

/** View Binding Examole activity
 * */
class ViewBindingExampleActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityViewBindingExampleBinding
    override fun onCreate(bundle: Bundle?){
        super.onCreate(bundle)
        mBinding = ActivityViewBindingExampleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.submitBtn.setOnClickListener {
            val enteredText = mBinding.enterTextET.text
            Toast.makeText(applicationContext,enteredText,Toast.LENGTH_SHORT).show()
        }
    }
}