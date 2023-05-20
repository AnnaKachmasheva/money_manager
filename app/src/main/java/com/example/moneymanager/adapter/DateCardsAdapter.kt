package com.example.moneymanager.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.DateModel
import com.example.sp_v2.R
import java.time.format.DateTimeFormatter

class DateCardsAdapter(
    dateModelArrayList: ArrayList<DateModel>
) :
    RecyclerView.Adapter<DateCardsAdapter.ViewHolder>() {

    private val dateModelArrayList: ArrayList<DateModel>
    private var selectedItemPosition: Int = 0
    private val greyColor: String = "#808080"
    private val whiteColor: String = "#FFFFFF"


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.date_card_fragment, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
            holder.dateCard.setBackgroundColor(Color.parseColor(greyColor))
            holder.date.setTextColor(Color.parseColor(whiteColor))
            holder.dateName.setTextColor(Color.parseColor(whiteColor))

        } else {
            holder.dateCard.setBackgroundColor(Color.parseColor(whiteColor))
            holder.date.setTextColor(Color.parseColor(greyColor))
            holder.dateName.setTextColor(Color.parseColor(greyColor))
        }
    }

    override fun getItemCount(): Int {
        return dateModelArrayList.size
    }

    fun addDate(model: DateModel) {
        if (dateModelArrayList.size > 2 )
            dateModelArrayList[2] = model
        else
            dateModelArrayList.add(model)

        notifyItemInserted(dateModelArrayList.size)
    }

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