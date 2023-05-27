package com.example.moneymanager.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.CategoryModel
import com.example.sp_v2.R

class CategoryCardsAdapter(
    categoryModelArrayList: LiveData<List<CategoryModel>>
) :
    RecyclerView.Adapter<CategoryCardsAdapter.ViewHolder>() {

    private var categoryModelArrayList: List<CategoryModel> = ArrayList()
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_card_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CategoryModel = categoryModelArrayList[position]
        holder.categoryName.text = model.name
        holder.categoryIcon.setImageResource(model.icon)
        holder.categoryItem.setBackgroundColor(Color.parseColor(model.color))

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

//        if (selectedItemPosition == position) {
//          holder.categoryItem.se
//        } else {
//        }
    }

    // todo change it
    private fun showUpdateDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(categoryModelArrayList[selectedItemPosition].name)
        val options = listOf("Update", "Delete")
        builder.setPositiveButton(options[0], null)
        builder.create()
        builder.show()
    }


    override fun getItemCount(): Int {
        return categoryModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView
        val categoryIcon: ImageView

        //        val categoryCard: MaterialCardView
        val categoryItem: View


        init {
            categoryName = itemView.findViewById(R.id.nameCategory)
            categoryIcon = itemView.findViewById(R.id.iconCategory)
//            categoryCard = itemView.findViewById(R.id.cardViewCategory)
            categoryItem = itemView.findViewById(R.id.category)
        }
    }

    init {
        if (categoryModelArrayList.value != null)
            this.categoryModelArrayList = categoryModelArrayList.value!!
    }
}