package com.example.todoit.common.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope
import java.lang.StringBuilder

class TodoViewModel (application: Application) : AndroidViewModel(application) {

    val read: LiveData<List<Todo>>
    private val repository: TodoRepository

    init{
        val todoDAO = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDAO)
        read = repository.read
    }

    suspend fun insert(todo: Todo){
        coroutineScope {
            repository.insert(todo)
        }
    }
    suspend fun update(todo: Todo){
        coroutineScope {
            repository.update(todo)
        }
    }
    suspend fun delete(todo: Todo){
        coroutineScope {
            repository.delete(todo)
        }
    }
}