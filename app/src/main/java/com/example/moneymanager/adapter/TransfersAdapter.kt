package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.main.MoneyManagerApp.Companion.datePattern
import com.example.moneymanager.main.MoneyManagerApp.Companion.numberFormat
import com.example.moneymanager.model.TransferModel
import com.example.moneymanager.ui.home.interfaces.TransferClickListener
import com.example.sp_v2.R
import java.time.format.DateTimeFormatter

class TransfersAdapter(private val clickListener: TransferClickListener) :
    RecyclerView.Adapter<TransfersAdapter.ViewHolder>() {

    private var transferModelArrayList = emptyList<TransferModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.transfer_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: TransferModel = transferModelArrayList[position]
        holder.icon.setImageResource(R.drawable.ic_down)
        holder.fromAccount.text = model.accountFrom?.name ?: ""
        holder.toAccount.text = model.accountTo?.name ?: ""
        holder.amount.text = prepareAmount(model.amount)
        holder.date.text = model.date.format(DateTimeFormatter.ofPattern(datePattern))

        holder.itemView.setOnClickListener {
            clickListener.onTransferClickListener(model)
        }
    }

    override fun getItemCount() = transferModelArrayList.size

    private fun prepareAmount(amount: Double): String {
        val dec = numberFormat
        return dec.format(amount).replace(",", " ")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fromAccount: TextView
        val toAccount: TextView
        val amount: TextView
        val date: TextView
        val icon: ImageView

        init {
            fromAccount = itemView.findViewById(R.id.fromAccountNameTransferItem)
            toAccount = itemView.findViewById(R.id.toAccountTransferItem)
            amount = itemView.findViewById(R.id.amountTransferItem)
            date = itemView.findViewById(R.id.dateTransferItem)
            icon = itemView.findViewById(R.id.iconTransferItem)
        }
    }

    fun setData(models: List<TransferModel>) {
        this.transferModelArrayList = models
        notifyDataSetChanged()
    }
}