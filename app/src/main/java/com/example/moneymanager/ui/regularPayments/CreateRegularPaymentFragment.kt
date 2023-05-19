package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sp_v2.databinding.FragmentCreateRegularPaymentBinding


class CreateRegularPaymentFragment : Fragment() {

    private var _binding: FragmentCreateRegularPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountsViewModel =
            ViewModelProvider(this)[RegularPaymentsViewModel::class.java]

        _binding = FragmentCreateRegularPaymentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
