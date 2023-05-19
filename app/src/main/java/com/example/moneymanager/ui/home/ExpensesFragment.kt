package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sp_v2.databinding.FragmentExpencesBinding

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpencesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}