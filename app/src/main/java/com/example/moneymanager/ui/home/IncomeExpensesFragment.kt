package com.example.moneymanager.ui.home

import android.app.AlertDialog
import android.graphics.Color
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
import com.example.sp_v2.databinding.FragmentTincomeExpemsesDetailsBinding

class IncomeExpensesFragment : Fragment() {

    private var _binding: FragmentTincomeExpemsesDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<IncomeExpensesFragmentArgs>()
    private lateinit var nHomeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTincomeExpemsesDetailsBinding.inflate(inflater, container, false)

        nHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val model = args.transactionModel

        binding.textAmount.text = model.amount.toString()
        binding.textType.text = model.type.type
        binding.textAccount.text = model.account?.name ?: ""
        model.category?.icon?.let { binding.iconCategory.setImageResource(it) }
        model.category?.color?.let { binding.iconCategory.setBackgroundColor(Color.parseColor(it)) }
        binding.textDate.text = model.date.toString()
        binding.textNote.text = model.note

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
                            IncomeExpensesFragmentDirections.actionIncomeExpensesFragmentToEditExpensesFragment(
                                args.transactionModel
                            )
                        findNavController().navigate(action)
                    }

                    R.id.delete -> {
                        openDialog()
                    }

                    else -> {
                        findNavController().popBackStack()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    private fun openDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete transaction?")
        alertDialogBuilder.setPositiveButton(
            "Yes"
        ) { dialog, _ ->
            deleteDataFromDatabase()
            findNavController().popBackStack()
            dialog.cancel()
        }
        alertDialogBuilder.setNegativeButton(
            "No"
        ) { dialog, _ ->
            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun deleteDataFromDatabase() {
        nHomeViewModel.deleteTransaction(args.transactionModel)
        Toast.makeText(requireContext(), "Transaction successfully deleted!", Toast.LENGTH_LONG)
            .show()
    }
}