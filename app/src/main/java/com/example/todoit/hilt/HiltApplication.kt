package com.example.todoit.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}