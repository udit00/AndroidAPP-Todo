package com.example.todoit.common.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.StringBuilder


@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey
    val id: Int,
    val todo: String,
    val isDone: Boolean
)
