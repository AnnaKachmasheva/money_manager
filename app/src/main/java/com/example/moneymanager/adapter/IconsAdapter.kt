package com.example.moneymanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sp_v2.R

class IconsAdapter(
    iconArrayList: ArrayList<Int>
) :
    RecyclerView.Adapter<IconsAdapter.ViewHolder>() {

    private val iconArrayList: ArrayList<Int>

    private var selectedItemPosition: Int = 0
    private val greyColor: String = "#808080"
    private val whiteColor: String = "#FFFFFF"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Int = iconArrayList[position]
        holder.iconView.setImageResource(model)

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

        if (selectedItemPosition == position) {
            holder.iconView.setBackgroundColor(Color.parseColor(greyColor))
        } else {
            holder.iconView.setBackgroundColor(Color.parseColor(whiteColor))
        }
    }

    override fun getItemCount(): Int {
        return iconArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconView: ImageView

        init {
            iconView = itemView.findViewById(R.id.icon)
        }
    }

    init {
        this.iconArrayList = iconArrayList
    }
}