package com.example.todoit.ui

import android.location.GnssAntennaInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoit.R
import com.example.todoit.data.Todo

class TodoAdapter(private val listener: TodoItemClicked) : RecyclerView.Adapter<TodoViewHolder>() {

    var todoList = ArrayList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        val todoViewHolder = TodoViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(todoList[todoViewHolder.adapterPosition])
        }
        return todoViewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.todo.text = todoList[position].todo

    }

    override fun getItemCount(): Int {
        return todoList.size
    }
    fun setData(todos: List<Todo>){
        todoList.clear()
        todoList.addAll(todos)
        notifyDataSetChanged()
    }

}
class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val todo: TextView = itemView.findViewById(R.id.todo);
    val haveToBeDone: ProgressBar = itemView.findViewById(R.id.haveToBeDone)
}
interface TodoItemClicked{
    fun onItemClicked(todo: Todo)
}