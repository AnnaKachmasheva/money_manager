package com.example.moneymanager.ui.home

import android.app.AlertDialog
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
import com.example.moneymanager.main.MoneyManagerApp
import com.example.moneymanager.main.MoneyManagerApp.Companion.numberFormat
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentTransferDetailsBinding
import java.time.format.DateTimeFormatter

class TransferFragment : Fragment() {

    private var _binding: FragmentTransferDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<TransferFragmentArgs>()
    private lateinit var nHomeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferDetailsBinding.inflate(inflater, container, false)

        nHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.textFromAccount.text = args.transferModel.accountFrom?.name ?: ""
        binding.textToAccount.text = args.transferModel.accountTo?.name ?: ""
        binding.textAmount.text = numberFormat.format(args.transferModel.amount).replace(",", " ")
        binding.textDate.text = args.transferModel.date.format(
            DateTimeFormatter.ofPattern(
                MoneyManagerApp.datePattern
            ))
        binding.textNote.text = args.transferModel.note

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
                            TransferFragmentDirections.actionTransferFragmentToEditTransferFragment(
                                args.transferModel
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
        alertDialogBuilder.setTitle("Delete account?")
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
        nHomeViewModel.deleteTransfer(args.transferModel)
        Toast.makeText(requireContext(), "Transfer successfully deleted!", Toast.LENGTH_LONG)
            .show()
    }
}