package com.example.moneymanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moneymanager.ui.home.CreateExpensesFragment
import com.example.moneymanager.ui.home.CreateIncomeFragment
import com.example.moneymanager.ui.home.CreateTransferFragment

class TransactionEditViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val NUM_TABS = 3

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CreateExpensesFragment()
            1 -> return CreateIncomeFragment()
        }
        return CreateTransferFragment()
    }
}