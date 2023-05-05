package com.example.todoit

import com.example.todoit.data.Todo
import retrofit2.Response
import retrofit2.http.GET

interface API {

    @GET
    fun getPosts(): Response<Todo>

}