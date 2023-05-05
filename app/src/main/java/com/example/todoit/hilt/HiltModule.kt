package com.example.todoit.hilt

import com.example.todoit.API
import com.google.gson.Gson
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    fun getApi(): API{
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}