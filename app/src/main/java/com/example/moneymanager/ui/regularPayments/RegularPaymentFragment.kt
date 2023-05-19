package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentsBinding

class RegularPaymentFragment : Fragment() {

    private var _binding: FragmentRegularPaymentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegularPaymentsBinding.inflate(inflater, container, false)
        val view: View = binding.root

        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.createRegularPaymentFragment)
        }



        return inflater.inflate(R.layout.fragment_regular_payment, container, false)
    }
}