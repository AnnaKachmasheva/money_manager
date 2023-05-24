package com.example.moneymanager.ui.regularPayments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.AccountsAdapter
import com.example.moneymanager.adapter.RegularPaymentsAdapter
import com.example.moneymanager.model.RegularPaymentModel
import com.example.moneymanager.utils.AccountItems
import com.example.moneymanager.utils.RegularPaymentsItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentsBinding
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.util.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editRegularPaymentFragment)
        }

        val layoutManagerAccount = LinearLayoutManager(activity)
        val regularPaymentsAdapter = RegularPaymentsAdapter(RegularPaymentsItems.RegularPaymentsItems)

        // Applying OnClickListener to our Adapter
        regularPaymentsAdapter.setOnClickListener(object :
            RegularPaymentsAdapter.OnClickListener {
            override fun onClick(position: Int, model: RegularPaymentModel) {
                val dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH)
                val bundle = bundleOf(
                    "type" to model.type.type,
                    "amount" to model.amount,
                    "name" to model.name,
                    "accountName" to model.account.name,
                    "categoryName" to model.category.name,
                    "categoryIcon" to model.category.icon,
                    "startDate" to model.startDate.format(dateFormat),
                    "endDate" to model.endDate.format(dateFormat),
                    "frequency" to model.frequency.frequency,
                    )

                view.findNavController().navigate(R.id.regularPaymentFragment, bundle)
            }
        })

        binding.regularPayments.apply {
            layoutManager = layoutManagerAccount
            adapter = regularPaymentsAdapter
        }

        // base divider
        binding.regularPayments.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManagerAccount.orientation
            )
        )
    }

//    companion object{
//        val NEXT_SCREEN="details_screen"
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}