package com.example.moneymanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moneymanager.ui.home.ExpensesFragment
import com.example.moneymanager.ui.home.IncomeFragment
import com.example.moneymanager.ui.home.TransfersFragment

private const val NUM_TABS = 3

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ExpensesFragment()
            1 -> return IncomeFragment()
        }
        return TransfersFragment()
    }
}