package com.example.todoit.common.utils

import android.app.Activity
import android.text.Editable
import android.util.Log
import com.example.todoit.common.environment.CommonResponse
import com.example.todoit.ui.addtodo.models.ColorsModel
import com.example.todoit.ui.login.LoginModel
import retrofit2.Response


class UTILS {
    companion object {
        lateinit var savedLoginModel: LoginModel

    }
}
fun getTodoColorsData(): ArrayList<ColorsModel>  {
    val colorList: ArrayList<ColorsModel> = ArrayList()
    colorList.add(ColorsModel("#FA003A", "#690709","RED"))
    colorList.add(ColorsModel("#0457B1", "#004496","BLUE"))
    colorList.add(ColorsModel("#43FA00", "#188c1e","GREEN"))//#066b06
//    colorList.add(ColorsModel("#43FA00", "#033805","GREEN"))//#066b06
    colorList.add(ColorsModel("#FF6F00", "#8c3d00","ORANGE"))
    colorList.add(ColorsModel("#555555", "#555555","GRAY"))
    colorList.add(ColorsModel("#FF000000", "#FF000000","BLACK"))
    return colorList
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