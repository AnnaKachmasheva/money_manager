package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentIncomeBinding

class IncomeFragment : Fragment() {

    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editTransactionFragment)
        }

        return view
    }

}