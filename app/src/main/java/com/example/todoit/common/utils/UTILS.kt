package com.example.todoit.common.utils

import android.app.Activity
import android.text.Editable
import android.util.Log

fun logger(activity: Activity, msg: String){
    Log.d(activity.localClassName, msg)
}

fun Editable.toString(): String {
    return Editable.Factory.getInstance().toString()
}

fun toEditable(string: String): Editable {
    if(string.isNullOrEmpty()) {
        return Editable.Factory.getInstance().newEditable("")
    }
    return Editable.Factory.getInstance().newEditable(string)
}