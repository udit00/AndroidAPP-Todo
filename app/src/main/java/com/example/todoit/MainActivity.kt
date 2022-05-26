package com.example.todoit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoit.data.Todo
import com.example.todoit.data.TodoViewModel
import com.example.todoit.ui.TodoAdapter
import com.example.todoit.ui.TodoItemClicked
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TodoItemClicked {
    lateinit var mTodoViewModel: TodoViewModel
    lateinit var todoList: RecyclerView
//    lateinit var todoDone: ProgressBar
    lateinit var addTodo: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoList = findViewById(R.id.todoList)
        addTodo = findViewById(R.id.addButton)
//        todoDone = findViewById(R.id.haveToBeDone)
        todoList.layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(this)
        todoList.adapter = adapter

        mTodoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[TodoViewModel::class.java]
//        mTodoViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//        )[TodoViewModel::class.java]
//        mTodoViewModel.read.observe(this@MainActivity, Observer { list ->
//            list?.let {
//                adapter.setData(it)
//            }
//        })

        addTodo.setOnClickListener {

        }
    }

    override fun onItemClicked(todo: Todo) {

    }
}