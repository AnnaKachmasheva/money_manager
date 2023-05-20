package com.example.moneymanager.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditMoneyAccountBinding


class EditMoneyAccountFragment : Fragment() {

    private var _binding: FragmentEditMoneyAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditMoneyAccountBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_edit_money_account, container, false);

        return view
    }
}