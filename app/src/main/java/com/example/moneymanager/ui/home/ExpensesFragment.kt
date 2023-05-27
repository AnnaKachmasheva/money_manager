package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.TransactionsAdapter
import com.example.moneymanager.utils.TransactionsItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentExpencesBinding

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpencesBinding.inflate(inflater, container, false)

        inflater.inflate(R.layout.fragment_expences, container, false)

        val view = binding.root

        val button = binding.addButton
        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editTransactionFragment)
        }

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        binding.expencesList.apply {
            layoutManager = LinearLayoutManager(activity)
//            adapter = TransactionsAdapter(TransactionsItems.TransactionsItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}