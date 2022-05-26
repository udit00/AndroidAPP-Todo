package com.example.todoit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class TodoRepository(private val todoDAO: TodoDAO) {

    val read: LiveData<List<Todo>> = todoDAO.read()

    suspend fun insert(todo: Todo){
        todoDAO.insert(todo)
    }
    suspend fun update(todo: Todo){
        todoDAO.update(todo)
    }
    suspend fun delete(todo: Todo){
        todoDAO.delete(todo)
    }
}