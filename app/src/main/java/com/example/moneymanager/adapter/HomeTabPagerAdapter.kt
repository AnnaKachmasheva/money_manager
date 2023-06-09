package com.example.moneymanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moneymanager.ui.home.ExpensesFragment
import com.example.moneymanager.ui.home.IncomesFragment
import com.example.moneymanager.ui.home.TransfersFragment

class HomeTabPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val NUM_TABS = 3
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ExpensesFragment()
            1 -> return IncomesFragment()
        }
        return TransfersFragment()
    }
}