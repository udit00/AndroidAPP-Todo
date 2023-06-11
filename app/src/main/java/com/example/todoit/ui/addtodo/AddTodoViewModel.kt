package com.example.todoit.ui.addtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoit.common.base.BaseViewModel
import com.example.todoit.ui.addtodo.models.AddTodoModel
import com.example.todoit.ui.addtodo.models.AddTodoTypeModel
import com.example.todoit.ui.addtodo.models.TodoTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(private val repo: AddTodoRepository) : BaseViewModel() {
    init {
        isError = repo.isError
    }
    val addTodoLiveData: LiveData<AddTodoModel>
    get() = repo.addTodoLiveData

    val todoTypesListLiveData: LiveData<ArrayList<TodoTypeModel>>
        get() = repo.todoTypeLiveData

    val addTodoTypeLiveData: LiveData<AddTodoTypeModel>
        get() = repo.addTodoTypeLiveData

    fun addTodo(userId: String, title: String, description: String,
                typeId: String, reminderDate: String, selectedColor: String) {
        viewModelScope.launch (Dispatchers.IO) {
            repo.addTodo(
                userId = userId,
                title = title,
                description = description,
                typeId = typeId,
                reminderDate = reminderDate,
                selectedColor = selectedColor
            )
        }
    }

    fun getTodoTypes(userId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            repo.getTodoTypes(
                userId = userId
            )
        }
    }

    fun addTodoTypes(userId: String, todoTypeName: String) {
        viewModelScope.launch (Dispatchers.IO) {
            repo.addTodoType(
                userId = userId,
                todoTypeName = todoTypeName
            )
        }
    }
}