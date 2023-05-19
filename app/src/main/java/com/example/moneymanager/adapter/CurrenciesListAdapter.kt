package com.example.moneymanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.CurrencyModel
import com.example.sp_v2.databinding.FragmentCurrencyListItemBinding


class CurrenciesListAdapter(private val onClick: (CurrencyModel) -> Unit) :
    ListAdapter<CurrencyModel, CurrenciesListAdapter.MyViewHolder>(CurrenciesDiffCallback()) {

    inner class MyViewHolder(private val binding: FragmentCurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyModel: CurrencyModel?) {
            binding.code.text = currencyModel?.code
            binding.name.text = currencyModel?.currencyName
//            Glide.with(binding.image)
//                .load(currencyModel?.flag)
//                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FragmentCurrencyListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val meme = getItem(position)
        holder.itemView.setOnClickListener {
//            onClickListener.onClick(meme)
        }
        holder.bind(meme)
    }

    class OnClickListener(val clickListener: (model: CurrencyModel) -> Unit) {
        fun onClick(model: CurrencyModel) = clickListener(model)
    }


    class CurrenciesDiffCallback() : DiffUtil.ItemCallback<CurrencyModel>() {
        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }
    }
}