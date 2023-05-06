package com.example.todoit.base

import androidx.lifecycle.MutableLiveData
import com.example.todoit.API
import javax.inject.Inject


open class BaseRepository {
    var isError= MutableLiveData<String>()

    @Inject
    lateinit var api: API

}