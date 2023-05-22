package com.example.moneymanager.ui.regularPayments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.RegularPaymentsAdapter
import com.example.moneymanager.utils.RegularPaymentsItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentsBinding

class RegularPaymentsFragment : Fragment() {

    private var _binding: FragmentRegularPaymentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[RegularPaymentsViewModel::class.java]

        _binding = FragmentRegularPaymentsBinding.inflate(inflater, container, false)
        val view: View = binding.root

        inflater.inflate(R.layout.fragment_regular_payments, container, false)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        _binding?.regularPayments?.apply {
            layoutManager = GridLayoutManager(activity, 1)
            adapter = RegularPaymentsAdapter(RegularPaymentsItems.RegularPaymentsItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}