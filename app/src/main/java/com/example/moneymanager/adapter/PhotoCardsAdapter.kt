package com.example.moneymanager.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.PhotoModel
import com.example.sp_v2.R

class PhotoCardsAdapter(
    dateModelArrayList: ArrayList<PhotoModel>
) :
    RecyclerView.Adapter<PhotoCardsAdapter.ViewHolder>() {

    private val dateModelArrayList: ArrayList<PhotoModel>
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: PhotoModel = dateModelArrayList[position]
//        holder.image = model.name

//        holder.date.text = model.date.format(formatter)

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

//        if (selectedItemPosition == position) {
//            holder.dateCard.setBackgroundColor(Color.parseColor(greyColor))
//        } else {
//            holder.dateCard.setBackgroundColor(Color.parseColor(whiteColor))
//        }
    }

    override fun getItemCount(): Int {
        return dateModelArrayList.size
    }

    fun addDate(model: PhotoModel) {
        dateModelArrayList.add(model)
        notifyItemInserted(dateModelArrayList.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView

        init {
            image = itemView.findViewById(R.id.transactionPhoto)
        }
    }

    init {
        this.dateModelArrayList = emptyList<PhotoModel>() as ArrayList<PhotoModel>
    }
}