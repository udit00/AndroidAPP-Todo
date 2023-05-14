package com.example.todoit.common.hilt

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}