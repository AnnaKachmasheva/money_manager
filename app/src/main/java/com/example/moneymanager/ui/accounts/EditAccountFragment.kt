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
import androidx.navigation.fragment.navArgs
import com.example.moneymanager.model.AccountModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditAccountBinding


class EditAccountFragment : Fragment() {

    private var _binding: FragmentEditAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAccountViewModel: AccountsViewModel
    private val args by navArgs<AccountFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAccountBinding.inflate(inflater, container, false)

        // set old data
        binding.nameText.setText(args.accountModel.name)
        binding.amountText.setText(args.accountModel.amount.toString())
        binding.switchPayment.isChecked = !args.accountModel.isIncludeInTotalBalance

        binding.editAccountButton.text = "Save"

        mAccountViewModel = ViewModelProvider(this)[AccountsViewModel::class.java]
        binding.editAccountButton.setOnClickListener {
            updateDataToDatabase()
        }
        return binding.root
    }


    private fun updateDataToDatabase() {
        val name = binding.nameText.text.toString().trim()
        val amount = binding.amountText.text.toString().trim()
        val isIncludeInTotalBalance = !binding.switchPayment.isChecked

        if (inputCheck(amount, name)) {
            val account = AccountModel(
                id = args.accountModel.id,
                name = name,
                amount = amount.toDouble(),
                isIncludeInTotalBalance = isIncludeInTotalBalance
            )
            mAccountViewModel.updateAccount(account)
            Toast.makeText(requireContext(), "Account successfully updated!", Toast.LENGTH_LONG)
                .show()
            val action =
                EditAccountFragmentDirections.actionAccountEditFragmentToAccountFragment2(account)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(amount: String, name: String): Boolean {
        return !(TextUtils.isEmpty(amount) && TextUtils.isEmpty(name)) && !TextUtils.isDigitsOnly(
            amount
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}