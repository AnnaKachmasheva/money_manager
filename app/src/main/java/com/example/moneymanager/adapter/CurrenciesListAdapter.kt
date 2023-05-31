package com.example.moneymanager.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.CurrencyModel
import com.example.sp_v2.R


class CurrenciesListAdapter(currencyModelsList: ArrayList<CurrencyModel>) :
    RecyclerView.Adapter<CurrenciesListAdapter.ViewHolder>() {

    private val currencyModelsList: ArrayList<CurrencyModel>
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_currency_list_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: CurrencyModel = currencyModelsList[position]
        holder.currencyCode.text = model.code
        holder.currencyName.text = model.currencyName
        holder.currencyFlag.setImageResource(model.flag)

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

        if (selectedItemPosition == position) {
            holder.currencyRow.setBackgroundResource(R.color.grey)
        } else {
            holder.currencyRow.setBackgroundResource(R.color.white)
        }
    }

    override fun getItemCount() = currencyModelsList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyCode: TextView
        val currencyName: TextView
        val currencyFlag: ImageView
        val currencyRow: View

        init {
            currencyCode = itemView.findViewById(R.id.code)
            currencyName = itemView.findViewById(R.id.name)
            currencyFlag = itemView.findViewById(R.id.image)
            currencyRow = itemView.findViewById(R.id.rowCurrency)
        }
    }

    init {
        this.currencyModelsList = currencyModelsList
    }
}