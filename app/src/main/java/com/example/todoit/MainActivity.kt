package com.example.todoit

import android.os.Bundle
import com.example.todoit.base.BaseActivity
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}