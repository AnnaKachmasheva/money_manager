package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.main.MoneyManagerApp.Companion.numberFormat
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.ui.accounts.AccountsFragmentDirections
import com.example.sp_v2.R

class AccountsAdapter :
    RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    private var accountList = emptyList<AccountModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.account_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: AccountModel = accountList[position]
        holder.accountName.text = model.name
        holder.accountAmount.text = prepareAmount(model.amount)

        holder.itemView.setOnClickListener {
            val action = AccountsFragmentDirections.actionNavAccountsToAccountFragment(model)
            holder.itemView.findNavController().navigate(action)
        }
    }

    private fun prepareAmount(amount: Double): String {
        val dec = numberFormat
        return dec.format(amount).replace(",", " ")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountName: TextView
        val accountAmount: TextView

        init {
            accountName = itemView.findViewById(R.id.nameAccount)
            accountAmount = itemView.findViewById(R.id.amountAccount)
        }
    }

    override fun getItemCount() = accountList.size


    fun setData(models: List<AccountModel>) {
        this.accountList = models
        notifyDataSetChanged()
    }

}