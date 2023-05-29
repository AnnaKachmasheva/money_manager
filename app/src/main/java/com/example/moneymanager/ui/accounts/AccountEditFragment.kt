package com.example.moneymanager.ui.accounts

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moneymanager.model.AccountModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditAccountBinding


class AccountEditFragment : Fragment() {

    private var _binding: FragmentEditAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAccountViewModel: AccountsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAccountBinding.inflate(inflater, container, false)
//        val view = inflater.inflate(R.layout.fragment_edit_account, container, false)
        mAccountViewModel = ViewModelProvider(this)[AccountsViewModel::class.java]
        binding.editAccountButton.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = binding.nameText.text.toString().trim()
        val amount = binding.amountText.text.toString().trim()
        val isIncludeInTotalBalance = !binding.switchPayment.isChecked

        if (inputCheck(amount, name)) {
            val account = AccountModel(
                id = 0,
                name = name,
                amount = amount.toDouble(),
                isIncludeInTotalBalance = isIncludeInTotalBalance
            )
            mAccountViewModel.addAccount(account)
            Toast.makeText(requireContext(), "Account successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createAccountFragment_to_nav_accounts)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(amount: String, name: String): Boolean {
        return !(TextUtils.isEmpty(amount) && TextUtils.isEmpty(name)) && !TextUtils.isDigitsOnly(amount)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}