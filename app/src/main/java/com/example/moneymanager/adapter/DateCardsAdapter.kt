package com.example.moneymanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.DateModel
import com.example.sp_v2.R
import java.time.format.DateTimeFormatter

class DateCardsAdapter(
    dateModelArrayList: ArrayList<DateModel>
) :
    RecyclerView.Adapter<DateCardsAdapter.ViewHolder>() {

    private val dateModelArrayList: ArrayList<DateModel>
    var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.date_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: DateModel = dateModelArrayList[position]
        holder.dateName.text = model.name

        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        holder.date.text = model.date.format(formatter)

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

        if (selectedItemPosition == position) {
            holder.dateCard.setBackgroundResource(R.color.grey)
            holder.date.setTextColor(Color.WHITE)
            holder.dateName.setTextColor(Color.WHITE)

        } else {
            holder.dateCard.setBackgroundResource(R.color.white)
            holder.date.setTextColor(Color.GRAY)
            holder.dateName.setTextColor(Color.GRAY)
        }
    }

    fun addDate(model: DateModel) {
        if (dateModelArrayList.size > 2)
            dateModelArrayList[2] = model
        else
            dateModelArrayList.add(model)

        notifyItemInserted(dateModelArrayList.size)
    }

    fun getSelectedDate() = dateModelArrayList[selectedItemPosition]

    override fun getItemCount() = dateModelArrayList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateName: TextView
        val date: TextView
        val dateCard: View

        init {
            date = itemView.findViewById(R.id.dayText)
            dateName = itemView.findViewById(R.id.dayNameTextLabel)
            dateCard = itemView.findViewById(R.id.dateCard)
        }
    }

    init {
        this.dateModelArrayList = dateModelArrayList
    }
}