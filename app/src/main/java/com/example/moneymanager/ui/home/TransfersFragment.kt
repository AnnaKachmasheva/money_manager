package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.TransfersAdapter
import com.example.moneymanager.model.TransferModel
import com.example.moneymanager.ui.home.interfaces.TransferClickListener
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentTransfersBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


class TransfersFragment : Fragment(), TransferClickListener {
    private var _binding: FragmentTransfersBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransfersBinding.inflate(inflater, container, false)
        val view = binding.root

        mHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val adapter = TransfersAdapter(this)
        val recyclerView = binding.transfersList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
        mHomeViewModel.readAllDataTransfer.observe(viewLifecycleOwner) { transfer ->
            adapter.setData(transfer)
        }

        mHomeViewModel.totalTransferAmount.observe(viewLifecycleOwner) { amount ->
            val transfersAmount = binding.transfersAmount
            transfersAmount.text = prepareAmount(amount ?: 0.0)
        }


        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.createTransferFragment)
        }

        return view
    }

    private fun prepareAmount(amount: Double): String {
        val dec = DecimalFormat("###,###,###,###,###.0", DecimalFormatSymbols(Locale.ENGLISH))
        return dec.format(amount).replace(",", " ") + " CZK"
    }

    override fun onTransferClickListener(model: TransferModel) {
        val action = HomeFragmentDirections.actionNavHomeToTransferFragment(model)
        Navigation.findNavController(binding.root).navigate(action)
    }

}