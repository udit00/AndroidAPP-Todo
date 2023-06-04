package com.example.todoit.common.data

data class TodoStatus(
    val code: Char,
    val title: String
){
    override fun toString(): String {
        return title
    }
}