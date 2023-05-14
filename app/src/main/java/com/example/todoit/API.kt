package com.example.todoit

import android.app.Activity
import com.example.todoit.common.data.Todo
import com.example.todoit.common.utils.logger
import com.example.todoit.ui.login.LoginModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("userLogin")
    fun userLogin(
       @Query("prmusername") userName: String,
       @Query("prmpassword") passWord: String
    ): Response<LoginModel>

    fun getPosts(activity: Activity) {
        logger(activity, "working")
    }

}