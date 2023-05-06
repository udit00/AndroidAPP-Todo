package com.example.todoit

import android.app.Activity
import com.example.todoit.data.Todo
import com.example.todoit.utils.logger
import retrofit2.Response
import retrofit2.http.GET

interface API {

    fun getPosts(activity: Activity) {
        logger(activity, "working")
    }

}