package com.example.moneymanager.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.AccountModel
import com.example.sp_v2.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class AccountsAdapter(
    accountModelArrayList: ArrayList<AccountModel>
) :
    RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    private val accountModelArrayList: ArrayList<AccountModel>
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.account_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: AccountModel = accountModelArrayList[position]
        holder.accountName.text = model.name
        holder.accountAmount.text = prepareAmount(model.amount)

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }

//        if (selectedItemPosition == position) {
//          holder.categoryItem.se
//        } else {
//        }
    }

    private fun prepareAmount(amount: Float): String {
        val dec = DecimalFormat("###,###,###,###,###.0", DecimalFormatSymbols(Locale.ENGLISH))
        return dec.format(amount).replace(",", " ")
    }

//    // todo change it
//    private fun showUpdateDialog(context: Context) {
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle(accountModelArrayList[selectedItemPosition].name)
//        val options = listOf("Update", "Delete")
//        builder.setPositiveButton(options[0], null)
//        builder.create()
//        builder.show()
//    }


    override fun getItemCount(): Int {
        return accountModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountName: TextView
        val accountAmount: TextView

        init {
            accountName = itemView.findViewById(R.id.nameAccount)
            accountAmount = itemView.findViewById(R.id.amountAccount)
        }
    }

    init {
        this.accountModelArrayList = accountModelArrayList
    }

}