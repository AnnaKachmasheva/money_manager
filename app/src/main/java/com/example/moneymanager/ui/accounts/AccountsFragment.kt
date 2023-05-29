package com.example.moneymanager.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.AccountsAdapter
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAccountsBinding


class AccountsFragment : Fragment() {

    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAccountViewModel: AccountsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val view = binding.root
//        val view = inflater.inflate(R.layout.fragment_accounts, container, false)
        mAccountViewModel = ViewModelProvider(this)[AccountsViewModel::class.java]


        val adapter = AccountsAdapter()
        val recyclerView = binding.accountList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.itemAnimator = DefaultItemAnimator();

        mAccountViewModel = ViewModelProvider(this)[AccountsViewModel::class.java]
        mAccountViewModel.readAllData.observe(viewLifecycleOwner, Observer { account ->
            adapter.setData(account)
        })

        val totalAmount = binding.totalAccounts
        totalAmount.text = adapter.getTotalBalance()

        val addButton = binding.addButton
        addButton.setOnClickListener() {
            findNavController().navigate(R.id.action_nav_accounts_to_editAccountFragment)
        }

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //set total amount
//        val totalAmount = binding.totalAccounts
//        totalAmount.text = mAccountViewModel.getTotalBalance()
////        else totalAmount.text = "0"
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}