package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.RegularPaymentModel
import com.example.moneymanager.ui.regularPayments.RegularPaymentsFragmentDirections
import com.example.moneymanager.ui.regularPayments.SwitchClickListener
import com.example.sp_v2.R

class RegularPaymentsAdapter(private val clickListener: SwitchClickListener) :
    RecyclerView.Adapter<RegularPaymentsAdapter.ViewHolder>() {

    var regularPaymentList = emptyList<RegularPaymentModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.regular_payment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: RegularPaymentModel = regularPaymentList[position]
        holder.regularPaymentName.text = model.name
        val isActive = model.isActive
        holder.swith.isChecked = isActive

        holder.regularPaymentName.setOnClickListener {
            val action =
                RegularPaymentsFragmentDirections.actionNavRegularPaymentsToRegularPaymentFragment(
                    model
                )
            holder.itemView.findNavController().navigate(action)
        }

        holder.swith.setOnClickListener() {
            clickListener.onSwitchClickListener(model)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val regularPaymentName: TextView
        val swith: Switch

        init {
            regularPaymentName = itemView.findViewById(R.id.nameRegularPayment)
            swith = itemView.findViewById(R.id.switchPayment)
        }
    }

    override fun getItemCount() = regularPaymentList.size

    fun setData(models: List<RegularPaymentModel>) {
        this.regularPaymentList = models
        notifyDataSetChanged()
    }

}