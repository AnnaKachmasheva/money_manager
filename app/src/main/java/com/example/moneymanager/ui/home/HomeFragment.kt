package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.moneymanager.adapter.HomeViewPagerAdapter
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val operations = arrayOf("Expenses", "Income", "Transfers")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val view = binding.viewPagerHome
        val tabLayout = binding.tabLayoutHome
        val adapter = HomeViewPagerAdapter(childFragmentManager, lifecycle)
        view.adapter = adapter

        TabLayoutMediator(tabLayout, view) { tab, position ->
            tab.text = operations[position]
        }.attach()


        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editTransactionFragment)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}