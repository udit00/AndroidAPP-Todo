package com.example.todoit.ui.addtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoit.common.base.BaseRepository
import com.example.todoit.common.utils.isResponseOk
import com.example.todoit.ui.addtodo.models.AddTodoModel
import com.example.todoit.ui.addtodo.models.AddTodoTypeModel
import com.example.todoit.ui.addtodo.models.TodoTypeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AddTodoRepository @Inject constructor(): BaseRepository() {
    private val addTodoMutData = MutableLiveData<AddTodoModel>()
    private val todoTypeListMutData = MutableLiveData<ArrayList<TodoTypeModel>>()
    private val addTodoTypeMutData = MutableLiveData<AddTodoTypeModel>()

    val addTodoLiveData: LiveData<AddTodoModel>
    get() = addTodoMutData

    val todoTypeLiveData: LiveData<ArrayList<TodoTypeModel>>
        get() = todoTypeListMutData

    val addTodoTypeLiveData: LiveData<AddTodoTypeModel>
        get() = addTodoTypeMutData

    suspend fun addTodo(userId: String, title: String, description: String,
                typeId: String, reminderDate: String, selectedColor: String) {
        val response = api.addTodo(
            userId = userId,
            title = title,
            description = description,
            todoTypeId = typeId,
            reminderDate = reminderDate,
            todoColor = selectedColor
        )
        if(isResponseOk(response)) {
            val gson = Gson()
            val resultStr = getResult0(response)
            response.body()?.data?.let {

                val result = object : TypeToken<List<AddTodoModel?>?>() {}.type
                val list: ArrayList<AddTodoModel> = gson.fromJson(resultStr, result);
                if(list.size > 0) {
                    addTodoMutData.postValue(list[0]);
                } else {
//                    isErrorData.postValue("No Todo Available.")
                }

            }
            if(response.body()?.data == null) {
                isErrorData.postValue(response.body()?.message)
            }
        }
    }

    suspend fun getTodoTypes(userId: String) {
        val response = api.getTodoTypes(
            userId = userId
        )
        if(isResponseOk(response)) {
            val gson = Gson()
            val resultStr = getResult0(response)
            response.body()?.data?.let {

                val result = object : TypeToken<List<TodoTypeModel?>?>() {}.type
                val list: ArrayList<TodoTypeModel> = gson.fromJson(resultStr, result);
                if(list.size > 0) {
                    todoTypeListMutData.postValue(list);
                } else {
//                    isErrorData.postValue("No Todo Available.")
                }

            }
            if(response.body()?.data == null) {
                isErrorData.postValue(response.body()?.message)
            }
        }

    }

    suspend fun addTodoType(userId: String, todoTypeName: String) {
        val response = api.addTodoType(
            userId = userId,
            todoTypeName = todoTypeName
        )
        if(isResponseOk(response)) {
            val gson = Gson()
            val resultStr = getResult0(response)
            response.body()?.data?.let {

                val result = object : TypeToken<List<AddTodoTypeModel?>?>() {}.type
                val list: ArrayList<AddTodoTypeModel> = gson.fromJson(resultStr, result);
                if(list.size > 0) {
                    addTodoTypeMutData.postValue(list[0]);
                } else {
//                    isErrorData.postValue("No Todo Available.")
                }

            }
            if(response.body()?.data == null) {
                isErrorData.postValue(response.body()?.message)
            }
        }

    }

}