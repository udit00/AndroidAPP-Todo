package com.example.todoit.common.onrowclick

interface OnRowClickListener<T> {
    fun onRowClick(clickedOn: String, data: T)
    fun onRowLongClick(clickedOn: String, data: T) {}
}