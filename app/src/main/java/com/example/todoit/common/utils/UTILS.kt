package com.example.todoit.common.utils

import android.app.Activity
import android.text.Editable
import android.util.Log
import com.example.todoit.common.environment.CommonResponse
import com.example.todoit.ui.login.LoginModel
import retrofit2.Response


class UTILS {
    companion object {
        lateinit var savedLoginModel: LoginModel
    }
}
fun logger(activity: Activity, msg: String){
    Log.d(activity.localClassName, msg)
}
fun isResponseOk(response: Response<CommonResponse>): Boolean{
    if(response.isSuccessful){
        if(response.body() != null){
            return true;
        }
    }
    return false;
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