package com.example.moneymanager.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.TransactionModel
import com.example.sp_v2.R

class TransactionsAdapter(
    transactionModelArrayList: ArrayList<TransactionModel>
) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private var transactionModelArrayList: ArrayList<TransactionModel>
    private var selectedItemPosition: Int = 0
    private val greyColor: String = "#808080"
    private val whiteColor: String = "#FFFFFF"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_or_income_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: TransactionModel = transactionModelArrayList[position]
        holder.categoryIcon.setImageResource(model.categoryModel.icon)
        holder.categoryIcon.setBackgroundColor(Color.parseColor(model.categoryModel.color))
        holder.categotyName.text = model.categoryModel.name
        holder.percent.text = "100" + "%"
        holder.amount.text = model.amount.toString()
        holder.currency.text = "CZK"

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

        if (selectedItemPosition == position) {
            holder.conteinerTransaction.setBackgroundColor(Color.parseColor(greyColor))
        } else {
            holder.conteinerTransaction.setBackgroundColor(Color.parseColor(whiteColor))
        }
    }

    override fun getItemCount(): Int {
        return transactionModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIcon: ImageView
        val categotyName: TextView
        val percent: TextView
        val amount: TextView
        val currency: TextView
        val conteinerTransaction: View

        init {
            categoryIcon = itemView.findViewById(R.id.iconCategoryEI)
            categotyName = itemView.findViewById(R.id.nameCategoryEI)
            percent = itemView.findViewById(R.id.percentEI)
            amount = itemView.findViewById(R.id.amountEI)
            currency = itemView.findViewById(R.id.currencyEI)
            conteinerTransaction = itemView.findViewById(R.id.dateCardEI)
        }
    }

    init {
        this.transactionModelArrayList = transactionModelArrayList
    }
}