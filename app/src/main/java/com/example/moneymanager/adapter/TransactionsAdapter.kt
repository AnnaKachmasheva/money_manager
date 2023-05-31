package com.example.moneymanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.ui.home.interfaces.TransactionClickListener
import com.example.sp_v2.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class TransactionsAdapter(private val clickListener: TransactionClickListener) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private var modelList: List<Pair<CategoryModel?, Double>> = emptyList()
    private var totalAmount = 0.0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_or_income_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        modelList[position].first?.let { holder.categoryIcon.setImageResource(it.icon) }
        holder.containerTransaction.setBackgroundColor(
            Color.parseColor(
                modelList[position].first?.color ?: ""
            )
        )
        holder.categoryName.text = modelList[position].first?.name ?: ""
        val amountCategory: Double? = modelList[position].second
        holder.percent.text = amountCategory?.div(totalAmount)?.times(100)?.let { prepareAmount(it) }
        holder.amount.text = amountCategory?.toString()
        holder.currency.text = "CZK"

        holder.itemView.setOnClickListener {
            clickListener.onTransactionClickListener(modelList[position])
        }

    }

    override fun getItemCount() = modelList.size

    private fun prepareAmount(amount: Double): String {
        val dec = DecimalFormat("###,###,###,###,###", DecimalFormatSymbols(Locale.ENGLISH))
        return dec.format(amount).replace(",", " ") + "%"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIcon: ImageView
        val categoryName: TextView
        val percent: TextView
        val amount: TextView
        val currency: TextView
        val containerTransaction: View

        init {
            categoryIcon = itemView.findViewById(R.id.iconCategoryEI)
            categoryName = itemView.findViewById(R.id.nameCategoryEI)
            percent = itemView.findViewById(R.id.percentEI)
            amount = itemView.findViewById(R.id.amountEI)
            currency = itemView.findViewById(R.id.currencyEI)
            containerTransaction = itemView.findViewById(R.id.dateCardEI)
        }
    }

    fun setData(expenses: List<ExpensesIncomeModel>?) {
        this.totalAmount = expenses?.sumOf { exp -> exp.amount }!!
        val modelListProm: Map<Int?, List<ExpensesIncomeModel>> =
            expenses.groupBy { exp -> exp.category?.id }

        val modelListResult: MutableList<Pair<CategoryModel?, Double>> = mutableListOf()
        modelListProm.forEach {
            val key = it.value[0].category
            val value = it.value.sumOf { exp -> exp.amount }
            val pair = Pair(key, value)
            modelListResult.add(pair)
        }

        this.modelList = modelListResult
        notifyDataSetChanged()
    }

}