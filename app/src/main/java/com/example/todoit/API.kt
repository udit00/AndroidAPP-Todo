package com.example.todoit

import com.example.todoit.common.environment.CommonResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @FormUrlEncoded
    @POST("userlogin")
    suspend fun userLogin(
       @Field("prmusername") userName: String,
       @Field("prmpassword") passWord: String,
       @Field("prmappversion") appVersion: String
    ): Response<CommonResponse>

    @GET("getTodos")
    suspend fun getTodos(
        @Query("prmuserid") userId: String
    ): Response<CommonResponse>

    @FormUrlEncoded
    @POST("addTodo")
    suspend fun addTodo(
        @Field("prmuserid") userId: String,
        @Field("prmtitle") title: String,
        @Field("prmdescription") description: String,
        @Field("prmtypeid") todoTypeId: String,
        @Field("prmremdt") reminderDate: String,
        @Field("prmcolor") todoColor: String
    ): Response<CommonResponse>

    @GET("getTodoTypes")
    suspend fun getTodoTypes(
        @Query("prmuserid") userId: String
    ): Response<CommonResponse>


    @FormUrlEncoded
    @POST("addTodoType")
    suspend fun addTodoType(
        @Field("prmuserid") userId: String,
        @Field("prmtodotype") todoTypeName: String,
    ): Response<CommonResponse>

}