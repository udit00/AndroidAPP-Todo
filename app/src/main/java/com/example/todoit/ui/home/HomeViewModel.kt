package com.example.todoit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoit.common.base.BaseViewModel
import com.example.todoit.common.data.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepository): BaseViewModel(){

    val todoList: LiveData<ArrayList<Todo>>
    get() = repo.todoList

    init {
        isError = repo.isError
    }
    fun callGetTodosApi(userId: String) {
        viewModelScope.launch (Dispatchers.IO){
            repo.callGetTodosApi(userId)
        }
    }
}