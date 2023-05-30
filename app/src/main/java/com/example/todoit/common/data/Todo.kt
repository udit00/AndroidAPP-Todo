package com.example.todoit.common.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.StringBuilder


@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey
    val id: Int,
    val todoTitle: String,
    val todoSubTitle: String,
    val todoType: TodoType,
    val isDone: Boolean
)
data class TodoType (
    val typeId: Int,
    val typeTitle: String,
    val typeDescription: String
)
