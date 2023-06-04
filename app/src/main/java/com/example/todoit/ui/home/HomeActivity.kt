package com.example.todoit.ui.home

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoit.R
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.common.data.Todo
import com.example.todoit.common.data.TodoStatus
import com.example.todoit.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: HomeTodoAdapter
    private var dataList: ArrayList<Todo> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var activityBinding: ActivityHomeBinding
    private var spinnerList: ArrayList<TodoStatus> = ArrayList()
    private lateinit var spinnerAdapter: ArrayAdapter<TodoStatus>
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
        lifecycleScope.launch(Dispatchers.IO) {
            val loginModel = getSavedUser()
            if(loginModel!=null) {
                successToast("Got Data")
                successToast(loginModel.message)
            }
        }
    }

    private fun getTodos() {
        viewModel.callGetTodosApi(
            "3"
        )
    }

    private fun initView() {
        rv = activityBinding.rv
        spinnerList.apply {
            add(TodoStatus('P', "Pending"))
            add(TodoStatus('W',"Working"))
            add(TodoStatus('C',"Completed"))
        }

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

    private fun setUpRecyclerView() {
        if(this::rvAdapter.isInitialized) {
            rvAdapter.setData(this, dataList, spinnerAdapter)
        } else {
            spinnerAdapter = ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                spinnerList
            )
            rvAdapter = HomeTodoAdapter()
            linearLayoutManager = LinearLayoutManager(this)
            rv.layoutManager = linearLayoutManager
            rv.adapter = rvAdapter
            rvAdapter.setData(this, dataList, spinnerAdapter)

        }
    }
}