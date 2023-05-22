package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moneymanager.adapter.HomeViewPagerAdapter
import com.example.moneymanager.adapter.TransactionEditViewPagerAdapter
import com.example.sp_v2.databinding.FragmentEditTransactionBinding
import com.google.android.material.tabs.TabLayoutMediator

class EditTransactionFragment : Fragment() {

    private var _binding: FragmentEditTransactionBinding? = null
    private val binding get() = _binding!!
    private val operations = arrayOf("Expenses", "Income", "Transfer")

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

//
//        val button = binding.addButton
//        button.setOnClickListener() {
//            Navigation.findNavController(view)
//                .navigate(R.id.editTransactionFragment)
//        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}