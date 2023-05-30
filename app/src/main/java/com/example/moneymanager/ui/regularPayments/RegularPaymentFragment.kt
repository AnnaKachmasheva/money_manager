package com.example.moneymanager.ui.regularPayments

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
import com.example.moneymanager.ui.accounts.AccountsViewModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentRegularPaymentBinding

class RegularPaymentFragment : Fragment() {

    private var _binding: FragmentRegularPaymentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<RegularPaymentFragmentArgs>()
    private lateinit var mRegularPaymentViewModel: RegularPaymentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegularPaymentBinding.inflate(inflater, container, false)
        val view: View = binding.root

        mRegularPaymentViewModel = ViewModelProvider(this)[RegularPaymentsViewModel::class.java]

        binding.textPaymentName.text = args.regularPayment.name
        binding.textPaymentType.text = args.regularPayment.type.toString()
        binding.textPaymentAmount.text = args.regularPayment.amount.toString()
        binding.textPaymentAccount.text = args.regularPayment.account?.name ?: "-"
        binding.textPaymentCategoryName.text = args.regularPayment.category?.name ?: "-"
        args.regularPayment.category?.icon?.let { binding.iconCategory.setImageResource(it) }
        args.regularPayment.category?.color?.let {
            binding.iconCategory.setBackgroundColor(
                Color.parseColor(
                    it
                )
            )
        }
        binding.textPaymentStartDate.text = args.regularPayment.startDate.toString()
        binding.textPaymentEndDate.text = args.regularPayment.endDate.toString()
        binding.textPaymentFrequency.text = args.regularPayment.frequency.toString()

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
                            RegularPaymentFragmentDirections.actionRegularPaymentFragmentToEditRegularPaymentFragment(
                                args.regularPayment
                            )
                        findNavController().navigate(action)
                    }

                    R.id.delete -> {
                        openDialog()
                    }

                    else -> {
                        val action =
                            RegularPaymentFragmentDirections.actionRegularPaymentFragmentToNavRegularPayments2()
                        findNavController().navigate(action)
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return view
    }

    private fun openDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete regular payment?")
        alertDialogBuilder.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            deleteDataFromDatabase()
            val action =
                RegularPaymentFragmentDirections.actionRegularPaymentFragmentToNavRegularPayments2()
            findNavController().navigate(action)
            dialog.cancel()
        }
        alertDialogBuilder.setNegativeButton(
            "No"
        ) { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun deleteDataFromDatabase() {
        mRegularPaymentViewModel.deleteRegularPayment(args.regularPayment)
        Toast.makeText(requireContext(), "Regular payment successfully deleted!", Toast.LENGTH_LONG)
            .show()
    }
}