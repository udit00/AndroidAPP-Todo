package com.example.todoit.common.hilt

import com.example.todoit.API
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun getApi(): API{
        return Retrofit.Builder()
                .baseUrl("http://192.168.29.188:5000/todo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
    }
}