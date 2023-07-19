package com.example.todoit.common.onrowclick

interface OnRowClickListener {
    fun onRowClick(clickedOn: String, data: Any)
    fun onRowLongClick(clickedOn: String, data: Any) {}
}