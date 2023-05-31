package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.RegularPaymentsAdapter
import com.example.moneymanager.model.RegularPaymentModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentsBinding

class RegularPaymentsFragment : Fragment(), SwitchClickListener {

    private var _binding: FragmentRegularPaymentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRegularPaymentsViewModel: RegularPaymentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegularPaymentsBinding.inflate(inflater, container, false)
        val view: View = binding.root

        mRegularPaymentsViewModel = ViewModelProvider(this)[RegularPaymentsViewModel::class.java]
        val adapter = RegularPaymentsAdapter(this)
        val recyclerView = binding.regularPayments
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()

        mRegularPaymentsViewModel.readAllData.observe(
            viewLifecycleOwner
        ) { regularPayments ->
            adapter.setData(regularPayments)
        }

        val button = binding.addButton
        button.setOnClickListener {
            findNavController().navigate(R.id.action_nav_regular_payments_to_createRegularPaymentFragment)
        }

        return view
    }

    override fun onSwitchClickListener(regularPaymentModel: RegularPaymentModel) {
        regularPaymentModel.isActive = !regularPaymentModel.isActive
        mRegularPaymentsViewModel.updateRegularPayment(regularPaymentModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}