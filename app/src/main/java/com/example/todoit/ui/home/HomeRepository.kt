package com.example.todoit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoit.common.base.BaseRepository
import com.example.todoit.common.data.Todo
import com.example.todoit.common.utils.isResponseOk
import com.example.todoit.ui.login.LoginModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class HomeRepository @Inject constructor(): BaseRepository(){
    private val todoListData= MutableLiveData<ArrayList<Todo>>()

    val todoList: LiveData<ArrayList<Todo>>
    get() = todoListData

    suspend fun callGetTodosApi(userId: String) {
        val response =api.getTodos(
            userId = userId
        )
        if(isResponseOk(response)) {
            val gson = Gson()
            var resultStr = getResult0(response)
            response.body()?.data?.let {

                val todoResult = object : TypeToken<List<Todo?>?>() {}.type
                val todoList: ArrayList<Todo> = gson.fromJson(resultStr, todoResult);
                if(todoList.size > 0) {
                    todoListData.postValue(todoList)
                }

            }
            if(response.body()?.data == null) {
                isError.postValue(response.body()?.message)
            }
        }
    }
}