package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.main.MoneyManagerApp.Companion.numberFormat
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.ui.home.TransactionsByCategoryFragmentDirections
import com.example.sp_v2.R

class TransactionByCategoryAdapter :
    RecyclerView.Adapter<TransactionByCategoryAdapter.ViewHolder>() {

    private var modelArrayList = emptyList<ExpensesIncomeModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExpensesIncomeModel = modelArrayList[position]
        holder.iconCategory.setImageResource(R.drawable.ic_minus)
        holder.nameCategory.text = model.category?.name ?: ""
        holder.amount.text = prepareAmount(model.amount)
        holder.currency.text = "CZK"

        holder.itemView.setOnClickListener {
            val action =
                TransactionsByCategoryFragmentDirections.actionTransactionsByCategoryFragmentToIncomeExpensesFragment(
                    model
                )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = modelArrayList.size

    private fun prepareAmount(amount: Double): String {
        val dec = numberFormat
        return dec.format(amount).replace(",", " ")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconCategory: ImageView
        val nameCategory: TextView
        val amount: TextView
        val currency: TextView

        init {
            iconCategory = itemView.findViewById(R.id.iconTransactionByCategory)
            nameCategory = itemView.findViewById(R.id.categoryNameTransactionByCategory)
            amount = itemView.findViewById(R.id.amountTransactionByCategory)
            currency = itemView.findViewById(R.id.currencyTransactionByCategory)
        }
    }

    fun setData(models: List<ExpensesIncomeModel>) {
        this.modelArrayList = models
        notifyDataSetChanged()
    }

}