package com.example.todoit.ui.home

import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoit.R
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.common.data.Todo
import com.example.todoit.common.data.TodoType
import com.example.todoit.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    lateinit var rv: RecyclerView
    lateinit var rvAdapter: HomeTodoAdapter
    lateinit var dataList: ArrayList<Todo>
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
    }

    private fun initView() {
        rv = activityBinding.rv
        dataList = fakeData()
    }

    private fun setUpObservers() {

    }

    private fun fakeData(): ArrayList<Todo> {
        val list = ArrayList<Todo>()
        for(i in 0 until 100) {
            list.add(
                Todo(
                    i,
                "item_${i}",
                    "subTitle_${i}",
                    TodoType(
                        i+1,
                        "Sports",
                        "Test"
                    ),
                    false
                )
            )
        }
        return list
    }

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