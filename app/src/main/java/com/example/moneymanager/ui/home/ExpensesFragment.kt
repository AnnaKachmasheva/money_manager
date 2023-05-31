package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.TransactionsAdapter
import com.example.moneymanager.main.MoneyManagerApp.Companion.numberFormat
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.ui.home.interfaces.TransactionClickListener
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentExpencesIncomeBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class ExpensesFragment : Fragment(), TransactionClickListener {

    private var _binding: FragmentExpencesIncomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpencesIncomeBinding.inflate(inflater, container, false)
        val view = binding.root

        mHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val adapter = TransactionsAdapter(this)
        val recyclerView = binding.expensesList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = DefaultItemAnimator()
        mHomeViewModel.readAllDataExpenses.observe(viewLifecycleOwner) { expenses ->
            adapter.setData(expenses)
        }

        mHomeViewModel.totalExpensesAmount.observe(viewLifecycleOwner) { amount ->
            val transfersAmount = binding.axpensesAmount
            transfersAmount.text = prepareAmount(amount ?: 0.0)
        }

        val button = binding.addButton
        button.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.editTransactionFragment)
        }

        return view
    }

    private fun prepareAmount(amount: Double): String {
        val dec = numberFormat
        return dec.format(amount).replace(",", " ") + " CZK"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTransactionClickListener(expensesIncomeModel: Pair<CategoryModel?, Double>) {
        val action = expensesIncomeModel.first?.let {
            HomeFragmentDirections.actionNavHomeToTransactionsByCategoryFragment(
                expensesIncomeModel.second.toLong(),
                it
            )
        }
        if (action != null) {
            Navigation.findNavController(binding.root).navigate(action)
        }

    }
}
