package com.example.todoit.ui.home

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoit.R
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.common.data.Todo
import com.example.todoit.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var rv: RecyclerView
    lateinit var rvAdapter: HomeTodoAdapter
    private var dataList: ArrayList<Todo> = ArrayList()
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var activityBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        setActionBar(activityBinding.toolBar as Toolbar)
        supportActionBar?.title = "Todo"
        initView()
        setUpObservers()
        setUpRecyclerView()
        getTodos()
    }

    private fun getTodos() {
        viewModel.callGetTodosApi(
            "3"
        )
    }

    private fun initView() {
        rv = activityBinding.rv
//        dataList = fakeData()
    }

    private fun setUpObservers() {
        viewModel.isError.observe(this) { error ->
            errorToast(error)
        }
        viewModel.todoList.observe(this) { result ->
            if(result != null) {
                dataList = result
                setUpRecyclerView()
            }
        }
    }

//    private fun fakeData(): ArrayList<Todo> {
//        val list = ArrayList<Todo>()
//        for(i in 0 until 100) {
//            list.add(
//                Todo(
//                    i,
//                "item_${i}",
//                    "subTitle_${i}",
//                    TodoType(
//                        i+1,
//                        "Sports",
//                        "Test"
//                    ),
//                    false
//                )
//            )
//        }
//        return list
//    }

    private fun setUpRecyclerView() {
        if(this::rvAdapter.isInitialized) {
            rvAdapter.setData(dataList)
        } else {
            rvAdapter = HomeTodoAdapter()
            linearLayoutManager = LinearLayoutManager(this)
            rv.layoutManager = linearLayoutManager
            rv.adapter = rvAdapter
            rvAdapter.setData(dataList)

        }
    }
}