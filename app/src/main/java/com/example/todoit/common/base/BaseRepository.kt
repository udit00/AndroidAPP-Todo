package com.example.todoit.common.base

import androidx.lifecycle.MutableLiveData
import com.example.todoit.API
import javax.inject.Inject


open class BaseRepository {
    var isError= MutableLiveData<String>()

    @Inject
    lateinit var api: API

}