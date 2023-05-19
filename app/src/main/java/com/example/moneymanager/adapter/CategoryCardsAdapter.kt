package com.example.moneymanager.adapter

import android.graphics.Color
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
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_card_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CategoryModel = courseModelArrayList[position]
        holder.categoryName.text = model.name
        holder.categoryIcon.setImageResource(model.icon)
        holder.categoryCard.setBackgroundColor(Color.parseColor(model.color))
    }

    override fun getItemCount(): Int {
        return courseModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView
        val categoryIcon: ImageView
        val categoryCard: View

        init {
            categoryName = itemView.findViewById(R.id.nameCategory)
            categoryIcon = itemView.findViewById(R.id.iconCategory)
            categoryCard = itemView.findViewById(R.id.categoriesView)
        }
    }

    init {
        this.courseModelArrayList = courseModelArrayList
    }
}