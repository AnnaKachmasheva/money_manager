package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.utils.IconItems
import com.example.sp_v2.R

class IconsAdapter :
    RecyclerView.Adapter<IconsAdapter.ViewHolder>() {

    private val iconArrayList: ArrayList<Int> = IconItems.IconItems

    private var selectedItemPosition: Int = 0

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
            holder.iconView.setBackgroundResource(R.color.grey)
        } else {
            holder.iconView.setBackgroundResource(R.color.white)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconView: ImageView

        init {
            iconView = itemView.findViewById(R.id.icon)
        }
    }

    override fun getItemCount() = iconArrayList.size

    fun getSelectedIcon() = iconArrayList[selectedItemPosition]

    fun setInitPosition(icon: Int) {
        selectedItemPosition = iconArrayList.indexOf(icon)
    }

}