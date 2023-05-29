package com.example.moneymanager.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.CategoryModel
import com.example.sp_v2.R

class CategoryCardsAdapter(private val onClickListener: (View, CategoryModel) -> Unit) :
    RecyclerView.Adapter<CategoryCardsAdapter.ViewHolder>() {

    private var categoryModelArrayList = emptyList<CategoryModel>()
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_card_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CategoryModel = categoryModelArrayList[position]
        holder.categoryName.text = model.name
        holder.categoryIcon.setImageResource(model.icon)
        holder.categoryItem.setBackgroundColor(Color.parseColor(model.color))

        holder.itemView.setOnClickListener { view ->
            onClickListener.invoke(view, model)
        }
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
        val categoryItem: View

        init {
            categoryName = itemView.findViewById(R.id.nameCategory)
            categoryIcon = itemView.findViewById(R.id.iconCategory)
            categoryItem = itemView.findViewById(R.id.category)
        }
    }

    fun setData(models: List<CategoryModel>) {
        this.categoryModelArrayList = models
        notifyDataSetChanged()
    }

}