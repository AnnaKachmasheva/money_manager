package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentBinding

class RegularPaymentFragment : Fragment() {

    private var _binding: FragmentRegularPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegularPaymentBinding.inflate(inflater, container, false)
        val view: View = binding.root

        return inflater.inflate(R.layout.fragment_regular_payment, container, false)
    }
}