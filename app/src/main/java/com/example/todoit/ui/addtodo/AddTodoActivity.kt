package com.example.todoit.ui.addtodo

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.widget.DrawableUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.todoit.R
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.common.onrowclick.OnRowClickListener
import com.example.todoit.common.utils.UTILS
import com.example.todoit.databinding.ActivityAddTodoBinding
import com.example.todoit.ui.addtodo.anim.startAnimation
import com.example.todoit.ui.addtodo.models.ColorsModel
import com.example.todoit.ui.addtodo.models.TodoTypeModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddTodoActivity @Inject constructor(): BaseActivity(), OnRowClickListener<ColorsModel> {

    private val viewModel: AddTodoViewModel by viewModels()
    private lateinit var rv: RecyclerView
    private var rvAdapter: ColorAdapter? = null
    private var dataList: ArrayList<ColorsModel> = ArrayList()
    private var popUpMenuList: ArrayList<TodoTypeModel> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var activityBinding: ActivityAddTodoBinding
    private lateinit var popupMenu: PopupMenu
//    private var spinnerList: ArrayList<TodoStatus> = ArrayList()
//    private lateinit var spinnerAdapter: ArrayAdapter<TodoStatus>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_todo)
        activityBinding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        initUi()
        setObservers()
        getTodoTypes()
        setUpClickListeners()
        setUpRecyclerView()
    }

    private fun setObservers() {
        viewModel.addTodoLiveData.observe(this) { result ->
            if(result.status == 1) {
                successToast(result.message)
            } else {
                errorToast(result.message)
            }
        }
        viewModel.todoTypesListLiveData.observe(this) { result ->
            if(result.size > 0) {
                popUpMenuList.addAll(result)
            }
        }
    }

    private fun showPopUpMenu(view: View, menuList: ArrayList<TodoTypeModel>) {
            popupMenu = PopupMenu(this, view)
            for(i in 0 until menuList.size) {
                popupMenu.menu.add(1, i, i, menuList.elementAt(i).type)
            }
            popupMenu.menu.add(1, menuList.size, menuList.size, "Add More")
            popupMenu.show()

    }


    private fun getTodoTypes() {
        viewModel.getTodoTypes(
            userId = UTILS.savedLoginModel.userid.toString(),
        )
    }

    private fun addTodoType(typeName: String) {
        viewModel.addTodoTypes(
            userId = UTILS.savedLoginModel.userid.toString(),
            todoTypeName = typeName
        )
    }

    private fun initUi() {
        rv = activityBinding.rvColor
        dataList = getTodoColorsData()
    }

    private fun setUpClickListeners() {
        activityBinding.inputType.setOnClickListener {
            showPopUpMenu(it, popUpMenuList)
        }
    }

    private fun getTodoColorsData(): ArrayList<ColorsModel>  {
        val colorList: ArrayList<ColorsModel> = ArrayList()
        colorList.add(ColorsModel("#FA003A", "RED"))
        colorList.add(ColorsModel("#0457B1", "BLUE"))
        colorList.add(ColorsModel("#43FA00", "GREEN"))
        colorList.add(ColorsModel("#FF6F00", "ORANGE"))
        colorList.add(ColorsModel("#555555", "GRAY"))
        colorList.add(ColorsModel("#FF000000", "BLACK"))
        return colorList

    }

    private fun setUpRecyclerView() {
        if(rvAdapter == null) {
            rvAdapter = ColorAdapter(this, dataList, this)
        }
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = HORIZONTAL
        rv.layoutManager = linearLayoutManager
        rv.adapter = rvAdapter
    }

    override fun onRowClick(clickedOn: String, data: ColorsModel) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.color_explosion_anim).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
        }
//        activityBinding.colorExplodeAnim.setBackgroundColor(Color.parseColor(data.colorHexa))
        activityBinding.colorExplodeAnim.backgroundTintList = ColorStateList.valueOf(Color.parseColor(data.colorHexa))
        activityBinding.colorExplodeAnim.startAnimation(animation) {
            activityBinding.addTodoParentLayout.setBackgroundColor(Color.parseColor(data.colorHexa))
        }
    }
}