package com.example.todoit.ui.addtodo.models

data class TodoTypeModel(
    val color: String,
    val createdon: String,
    val id: Int,
    val message: String,
    val status: Int,
    val type: String,
    val userid: Int
)