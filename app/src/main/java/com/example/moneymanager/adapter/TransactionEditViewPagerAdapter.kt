package com.example.moneymanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moneymanager.ui.home.ExpensesEditFragment
import com.example.moneymanager.ui.home.IncomeEditFragment
import com.example.moneymanager.ui.home.TransferEditFragment

class TransactionEditViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ExpensesEditFragment()
            1 -> return IncomeEditFragment()
        }
        return TransferEditFragment()
    }
}