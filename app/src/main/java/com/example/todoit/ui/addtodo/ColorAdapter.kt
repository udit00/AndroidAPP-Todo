package com.example.todoit.ui.addtodo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.todoit.common.onrowclick.OnRowClickListener
import com.example.todoit.databinding.ItemTodoColorBinding
import com.example.todoit.ui.addtodo.models.ColorsModel
import com.google.android.material.imageview.ShapeableImageView

class ColorAdapter(private val rvList: ArrayList<ColorsModel>, val onRowClickListener: OnRowClickListener<ColorsModel>): RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    class ColorViewHolder(private val itemBinding: ItemTodoColorBinding): RecyclerView.ViewHolder(itemBinding.root) {
        var todoColor: ShapeableImageView = itemBinding.todoColor
        fun bind(colorsModel: ColorsModel) {
//            itemBinding.model = colorsModel
            itemBinding.todoColor.setBackgroundColor(Color.parseColor(colorsModel.colorHexa))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = ItemTodoColorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ColorViewHolder(view)
        viewHolder.todoColor.setOnClickListener {
            onRowClickListener.onRowClick("COLOR", rvList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return rvList.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(rvList[holder.adapterPosition])
    }
}