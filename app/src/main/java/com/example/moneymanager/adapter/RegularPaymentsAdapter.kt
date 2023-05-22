package com.example.moneymanager.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.RegularPaymentModel
import com.example.sp_v2.R

class RegularPaymentsAdapter(
    regularPaymentsModelArrayList: ArrayList<RegularPaymentModel>,
    private val listener: (RegularPaymentModel) -> Unit
) :
    RecyclerView.Adapter<RegularPaymentsAdapter.ViewHolder>() {

    private val modelArrayList: ArrayList<RegularPaymentModel>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.regular_payment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: RegularPaymentModel = modelArrayList[position]
        holder.regularPaymentName.text = model.name
        val isActive = model.isActive
        holder.swith.isChecked = isActive

        holder.regularPaymentName.setOnClickListener {
            listener(modelArrayList[position])
        }
    }

    class OnClickListener(val clickListener: (model: RegularPaymentModel) -> Unit) {
        fun onClick(model: RegularPaymentModel) = clickListener(model)
    }

    override fun getItemCount() = modelArrayList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val regularPaymentName: TextView

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val swith: Switch

        init {
            regularPaymentName = itemView.findViewById(R.id.nameRegularPayment)
            swith = itemView.findViewById(R.id.switchPayment)
        }
    }

    init {
        this.modelArrayList = regularPaymentsModelArrayList
    }
}