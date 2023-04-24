package com.example.todoit.base

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

    public fun successToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }
    public fun errorToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }


}