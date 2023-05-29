package com.example.moneymanager.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AccountFragmentArgs>()
    private lateinit var mAccountViewModel: AccountsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.textAccountName.text = args.accountModel.name
        binding.textAccountAmount.text = args.accountModel.amount.toString()

        mAccountViewModel = ViewModelProvider(this)[AccountsViewModel::class.java]

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.findItem(R.id.edit).isVisible = true
                menu.findItem(R.id.delete).isVisible = true
                menuInflater.inflate(R.menu.navigation, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.edit -> {
                        val action =
                            AccountFragmentDirections.actionAccountFragmentToAccountEditFragment(
                                args.accountModel
                            )
                        findNavController().navigate(action)
                    }

                    R.id.delete -> {
                        deleteDataFromDatabase()
                        val action = AccountFragmentDirections.actionAccountFragmentToNavAccounts()
                        findNavController().navigate(action)
                    }

                    else -> {
                        val action = AccountFragmentDirections.actionAccountFragmentToNavAccounts()
                        findNavController().navigate(action)
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    fun deleteDataFromDatabase() {
        mAccountViewModel.deleteAccount(args.accountModel)
        Toast.makeText(requireContext(), "Account successfully deleted!", Toast.LENGTH_LONG)
            .show()
    }
}