package com.example.moneymanager.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.AccountsAdapter
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.utils.AccountItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAccountsBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class AccountsFragment : Fragment() {

    private var _binding: FragmentAccountsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountsViewModel =
            ViewModelProvider(this)[AccountsViewModel::class.java]

        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerAccount = LinearLayoutManager(activity)
        binding.accountList.apply {
            layoutManager = layoutManagerAccount
            adapter = AccountsAdapter(AccountItems.AccountItems)
        }

        // base divider
        binding.accountList.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManagerAccount.orientation
            )
        )

        val buttonContactUs = binding.addButton
        buttonContactUs.setOnClickListener() { view ->
            view.findNavController().navigate(R.id.createAccountFragment)
        }

        //set total amount
        val totalAmount = binding.totalAccounts
        totalAmount.text = getTotalAmount(AccountItems.AccountItems)

    }

    private fun getTotalAmount(accountItems: ArrayList<AccountModel>): String {
        val totalAmount = accountItems.filter { account -> account.isIncludeInTotalBalance }
            .map { account -> account.amount }
            .sum()

        val dec = DecimalFormat("###,###,###,###,###.0", DecimalFormatSymbols(Locale.ENGLISH))
        return dec.format(totalAmount).replace(",", " ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}