package com.example.moneymanager.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.utils.ColorItems
import com.example.sp_v2.R

class ColorsAdapter() :
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    private val dateModelArrayList: ArrayList<String>
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: String = dateModelArrayList[position]
        holder.colorView.setBackgroundColor(Color.parseColor(model))

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }


        if (selectedItemPosition == position) {
            holder.colorView.setImageResource(R.drawable.ic_check)
        } else {
            holder.colorView.setImageResource(0)
        }
    }

    override fun getItemCount(): Int {
        return dateModelArrayList.size
    }

    fun getSelectedColor(): String {
        return dateModelArrayList[selectedItemPosition]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView: ImageView

        init {
            colorView = itemView.findViewById(R.id.color)
        }
    }

    init {
        this.dateModelArrayList = ColorItems.ColorItems
    }
}