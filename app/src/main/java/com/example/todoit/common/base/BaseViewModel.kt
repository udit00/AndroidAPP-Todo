package com.example.todoit.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var baseRepo: BaseRepository

    private val isErrorData: MutableLiveData<String> = MutableLiveData()
    val isError: LiveData<String>
    get() = baseRepo.isError

}