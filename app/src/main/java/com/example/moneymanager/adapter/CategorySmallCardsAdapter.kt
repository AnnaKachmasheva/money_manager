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

class CategorySmallCardsAdapter(
    categoryModelArrayList: ArrayList<CategoryModel>
) :
    RecyclerView.Adapter<CategorySmallCardsAdapter.ViewHolder>() {

    private val categoryModelArrayList: ArrayList<CategoryModel>

    private var selectedItemPosition: Int = 0
    private val greyColor: String = "#808080"
    private val whiteColor: String = "#FFFFFF"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_small_card_fragment, parent, false)
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
            holder.cardCategoryView.setBackgroundColor(Color.parseColor(greyColor))
            holder.categoryName.setTextColor(Color.parseColor(whiteColor))

        } else {
            holder.cardCategoryView.setBackgroundColor(Color.parseColor(whiteColor))
            holder.categoryName.setTextColor(Color.parseColor(greyColor))
        }
    }

    override fun getItemCount(): Int {
        return categoryModelArrayList.size
    }

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

    init {
        this.categoryModelArrayList = categoryModelArrayList
    }
}