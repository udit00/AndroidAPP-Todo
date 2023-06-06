package com.example.todoit.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {
//    protected var isErrorData: MutableLiveData<String> = MutableLiveData()

    lateinit var isError: LiveData<String>
}