package com.example.todoit.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.todoit.R

open class BaseActivity: AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var mActivity: Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
        mActivity = this@BaseActivity
    }

    public fun successToast(msg: String) {
        val view = layoutInflater.inflate(R.layout.toast, null)
        view.rootView.background = ContextCompat.getDrawable(this, R.color.success)
        val toastMessage = view.findViewById<TextView>(R.id.toast_msg)
//        toastMessage.backgroundTintMode = ContextCompat.getDrawable(this,R.color.success)
        toastMessage.background = ContextCompat.getDrawable(this, R.color.black)

        Toast(mContext).apply {
            setText(msg)
            duration = Toast.LENGTH_LONG
            setContentView(view)
            show()
        }

    }


}