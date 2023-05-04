package com.example.todoit.utils

import android.app.Activity
import android.util.Log

fun logger(activity: Activity, msg: String){
    Log.d(activity.localClassName, msg)
}