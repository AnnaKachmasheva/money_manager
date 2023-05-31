package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moneymanager.adapter.TransactionEditViewPagerAdapter
import com.example.moneymanager.model.enums.TransactionType
import com.example.sp_v2.databinding.FragmentEditTransactionBinding
import com.google.android.material.tabs.TabLayoutMediator

class EditTransactionFragment : Fragment() {

    private var _binding: FragmentEditTransactionBinding? = null
    private val binding get() = _binding!!
    private val operations = arrayOf(
        TransactionType.EXPENSES.type,
        TransactionType.INCOME.type,
        TransactionType.TRANSFER.type
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTransactionBinding.inflate(inflater, container, false)

        val view = binding.viewPagerEditTransaction
        val tabLayout = binding.tabLayoutEditTransaction
        val adapter = TransactionEditViewPagerAdapter(childFragmentManager, lifecycle)
        view.adapter = adapter

        TabLayoutMediator(tabLayout, view) { tab, position ->
            tab.text = operations[position]
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}