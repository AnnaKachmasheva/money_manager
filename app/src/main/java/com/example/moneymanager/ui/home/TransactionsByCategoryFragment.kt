package com.example.moneymanager.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.TransactionByCategoryAdapter
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentTransactionsByCategoryBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class TransactionsByCategoryFragment : Fragment() {

    private var _binding: FragmentTransactionsByCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var adapter: TransactionByCategoryAdapter

    private val args by navArgs<TransactionsByCategoryFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsByCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        mHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        initData()

        val categoryName = args.categoryModel.name
        val totalCategory = args.totalCategory

        binding.nameCategory.text = categoryName
        binding.categoryAmount.text = prepareAmount(totalCategory)
        binding.cardViewCategory.setCardBackgroundColor(Color.parseColor(args.categoryModel.color))

//        mHomeViewModel.readAllDataExpenses.observe(viewLifecycleOwner) {
//            adapter.setData(transactions)
//        }


        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editTransactionFragment)
        }

        return view
    }

    private fun prepareAmount(amount: Long): String {
        val dec = DecimalFormat("###,###,###,###,###.0", DecimalFormatSymbols(Locale.ENGLISH))
        return dec.format(amount).replace(",", " ") + " CZK"
    }

    private fun initData() {
        adapter = TransactionByCategoryAdapter()
        val recyclerView = binding.transactionsList
        mHomeViewModel.readAllDataExpenses.observe(viewLifecycleOwner) { expences ->
            val listTransaction =
                expences.filter { ex -> ex.category!!.id == args.categoryModel.id }
                    .toList().sortedBy { exp ->exp.date }
            adapter.setData(listTransaction)
        }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = DefaultItemAnimator()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}