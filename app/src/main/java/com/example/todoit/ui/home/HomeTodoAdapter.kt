package com.example.todoit.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoit.R
import com.example.todoit.common.data.Todo
import com.example.todoit.common.data.TodoStatus
import com.example.todoit.common.utils.logger
import com.example.todoit.databinding.ItemTodoBinding

class HomeTodoAdapter: RecyclerView.Adapter<HomeTodoAdapter.HomeTodoViewHolder>() {
    private lateinit var itemBinding: ItemTodoBinding
    var dataList= ArrayList<Todo>()
    private lateinit var spinnerData: ArrayAdapter<TodoStatus>
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoViewHolder {
        itemBinding= ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
//        itemBinding= ItemTodoBinding.bind(view)
        val viewHolder = HomeTodoViewHolder(itemBinding)
        itemBinding.todo.setOnClickListener {
        }
        return viewHolder
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeTodoViewHolder, position: Int) {
        holder.bind(dataList[holder.adapterPosition], spinnerData)
    }

    fun setData(
        mContext: Context,
        newDataList: ArrayList<Todo>,
        spinnerData: ArrayAdapter<TodoStatus>) {
        this.mContext = mContext
        this.dataList = newDataList
        this.spinnerData = spinnerData
        notifyDataSetChanged()
    }


    class HomeTodoViewHolder(private var itemTodoBinding: ItemTodoBinding): RecyclerView.ViewHolder(itemTodoBinding.root) {
        fun bind(todo: Todo, spinnerData: ArrayAdapter<TodoStatus>) {
            itemTodoBinding.todoItem = todo
            itemTodoBinding.haveToBeDone.adapter = spinnerData
        }
    }
}
