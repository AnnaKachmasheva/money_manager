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

class CategorySmallCardsAdapter :
    RecyclerView.Adapter<CategorySmallCardsAdapter.ViewHolder>() {

    private var categoryModelArrayList = emptyArray<CategoryModel>()
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_small_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CategoryModel = categoryModelArrayList[position]
        holder.categoryName.text = model.name
        holder.categoryIcon.setImageResource(model.icon)
        holder.categoryIcon.setBackgroundColor(Color.parseColor(model.color))

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

        if (selectedItemPosition == position) {
            holder.cardCategoryView.setBackgroundResource(R.color.grey)
        } else {
            holder.cardCategoryView.setBackgroundResource(R.color.white)
        }
    }

    override fun getItemCount() = categoryModelArrayList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView
        val categoryIcon: ImageView
        val cardCategoryView: View

        init {
            categoryName = itemView.findViewById(R.id.nameCategory)
            categoryIcon = itemView.findViewById(R.id.iconCategory)
            cardCategoryView = itemView.findViewById(R.id.cardCategory)
        }
    }

    fun getSelectedCategory(): CategoryModel? =
        if (categoryModelArrayList.isNotEmpty()) categoryModelArrayList[selectedItemPosition]
        else null

    fun setInitPosition(categoryModel: CategoryModel) {
        selectedItemPosition = categoryModelArrayList.indexOf(categoryModel)
    }

    fun setData(models: Array<CategoryModel>?) {
        if (models != null) {
            this.categoryModelArrayList = models
        }
        notifyDataSetChanged()
    }

}