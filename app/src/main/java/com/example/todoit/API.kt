package com.example.todoit

import android.app.Activity
import com.example.todoit.common.environment.CommonResponse
import com.example.todoit.common.utils.logger
import com.example.todoit.ui.login.LoginModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {

    @FormUrlEncoded
    @POST("userlogin")
    suspend fun userLogin(
       @Field("prmusername") userName: String,
       @Field("prmpassword") passWord: String
    ): Response<CommonResponse>

    suspend fun getPosts(activity: Activity) {
        logger(activity, "working")
    }

}