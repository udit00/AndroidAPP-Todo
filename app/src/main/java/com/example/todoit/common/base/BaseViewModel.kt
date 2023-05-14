package com.example.todoit.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

open class BaseViewModel: ViewModel() {
    val isError: MutableLiveData<String> = MutableLiveData()
}