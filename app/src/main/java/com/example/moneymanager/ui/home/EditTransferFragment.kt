package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.DateCardsAdapter
import com.example.moneymanager.model.DateModel
import com.example.moneymanager.model.TransferModel
import com.example.moneymanager.model.enums.TransactionType
import com.example.moneymanager.utils.DateItems
import com.example.sp_v2.databinding.FragmentEditTransferBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class EditTransferFragment : Fragment() {

    private var _binding: FragmentEditTransferBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: String = ""
    private lateinit var dateCardAdapter: DateCardsAdapter
    private lateinit var mHomeViewModel: HomeViewModel
    private var oldAccountAmountFrom: Double = 0.0
    private var oldAccountAmountTo: Double = 0.0
    private var oldAmount: Double = 0.0


    private val args by navArgs<EditTransferFragmentArgs>()

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTransferBinding.inflate(inflater, container, false)

        mHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        initData()

        // set old data
        binding.amountEditTransferText.setText(args.transferModel.amount.toString())
        oldAccountAmountFrom = args.transferModel.accountFrom?.amount ?: 0.0
        oldAccountAmountTo = args.transferModel.accountTo?.amount ?: 0.0
        oldAmount = args.transferModel.amount

        //date picker
        val datePickerButton = binding.openDatePicker
        datePickerButton.setOnClickListener() {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    if (date != null) {
                        selectedDate = date
                        date
                        dateCardAdapter.addDate(
                            DateModel(
                                name = "Selected",
                                date = LocalDate.parse(
                                    selectedDate,
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                                )
                            )
                        )
                        dateCardAdapter.selectedItemPosition = 2
                        dateCardAdapter.notifyDataSetChanged()
                    }
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }

        val createButton = binding.createButton
        createButton.setOnClickListener {
            updateData()
        }

        return binding.root
    }

    private fun initData() {
        val accounts = this@EditTransferFragment.context?.let {
            ArrayAdapter<Any>(
                it,
                android.R.layout.simple_spinner_item
            )
        }
        mHomeViewModel.readAllAccounts.observe(viewLifecycleOwner) { accountList ->
            accountList?.forEach {
                accounts?.add(it.name)
                accounts?.getPosition(args.transferModel.accountFrom?.name)
                    ?.let { binding.selectFromAccountSpinner.setSelection(it) }
                accounts?.getPosition(args.transferModel.accountTo?.name)
                    ?.let { binding.selectToAccountSpinner.setSelection(it) }
            }
        }
        binding.selectToAccountSpinner.adapter = accounts
        binding.selectFromAccountSpinner.adapter = accounts
    }

    private fun updateData() {
        val accountFromPosition = binding.selectFromAccountSpinner.selectedItemPosition
        val accountToPosition = binding.selectToAccountSpinner.selectedItemPosition
        val amount = binding.amountEditTransferText.text.toString().trim()
        val date = dateCardAdapter.getSelectedDate()
        val note = binding.noteText.text.toString().trim()

        if (accountFromPosition == accountToPosition) {
            Toast.makeText(requireContext(), "Please select different accounts.", Toast.LENGTH_LONG)
                .show()
        } else if (amount.isEmpty() || !amount.isDigitsOnly()) {
            Toast.makeText(
                requireContext(),
                "Please enter the amount as a number.",
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            val amountTransfer = amount.toDouble()
            val accountFrom = mHomeViewModel.readAllAccounts.value?.get(accountFromPosition)
            accountFrom?.minusAmount(amountTransfer)
            val accountTo = mHomeViewModel.readAllAccounts.value?.get(accountToPosition)
            accountTo?.plusAmount(amountTransfer)

            val transfer = TransferModel(
                id = args.transferModel.id,
                type = TransactionType.TRANSFER,
                amount = amountTransfer,
                date = date.date,
                note = note,
                accountFrom = accountFrom,
                accountTo = accountTo
            )
            //update transfer and accounts
            mHomeViewModel.updateTransfer(
                transfer,
                oldAccountAmountFrom,
                oldAccountAmountTo,
                oldAmount
            )

            Toast.makeText(requireContext(), "Transfer successfully updated!", Toast.LENGTH_LONG)
                .show()
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        _binding?.days?.apply {
            layoutManager = GridLayoutManager(activity, 3)
            dateCardAdapter = DateCardsAdapter(DateItems.DateItems)
            adapter = dateCardAdapter
        }

    }

}