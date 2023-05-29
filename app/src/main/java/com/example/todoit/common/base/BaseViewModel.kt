package com.example.todoit.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

open class BaseViewModel: ViewModel() {
    private val isErrorData: MutableLiveData<String> = MutableLiveData()
    lateinit var isError: LiveData<String>
}