package com.example.todoit.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.todoit.R
import com.example.todoit.common.environment.CommonResponse
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity @Inject constructor(): AppCompatActivity() {


    lateinit var mContext: Context
    lateinit var mActivity: Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
    }

    fun successToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }
    fun errorToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }



}