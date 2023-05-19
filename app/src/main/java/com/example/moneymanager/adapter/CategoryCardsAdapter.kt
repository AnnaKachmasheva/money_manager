package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.CategoryModel
import com.example.sp_v2.R

class CategoryCardsAdapter(
    courseModelArrayList: ArrayList<CategoryModel>
) :
    RecyclerView.Adapter<CategoryCardsAdapter.ViewHolder>() {
    private val courseModelArrayList: ArrayList<CategoryModel>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CategoryModel = courseModelArrayList[position]
        holder.categoryName.text = model.name
        holder.categoryIcon.setImageResource(model.icon)
    }

    override fun getItemCount(): Int {
        return courseModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView
        val categoryIcon: ImageView

        init {
            categoryName = itemView.findViewById(R.id.nameCategory)
            categoryIcon = itemView.findViewById(R.id.iconCategory)
        }
    }

    init {
        this.courseModelArrayList = courseModelArrayList
    }
}