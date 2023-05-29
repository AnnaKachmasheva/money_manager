package com.example.moneymanager.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AccountFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.textAccountName.text = args.accountModel.name
        binding.textAccountAmount.text = args.accountModel.amount.toString()



        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.edit).isVisible = true
        menu.findItem(R.id.delete).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }
}