package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.CategorySmallCardsAdapter
import com.example.moneymanager.model.RegularPaymentModel
import com.example.moneymanager.model.enums.FrequencyRegularPayment
import com.example.moneymanager.model.enums.TransactionType
import com.example.moneymanager.ui.home.DatePickerFragment
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditRegularPaymentBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CreateRegularPaymentFragment : Fragment() {

    private var _binding: FragmentEditRegularPaymentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRegularPaymentsViewModel: RegularPaymentsViewModel
    private lateinit var categoriesAdapter: CategorySmallCardsAdapter

    private val typesRegularPayment = arrayOf(
        TransactionType.EXPENSES.type,
        TransactionType.INCOME.type
    )

    private val frequenciesRegularPayment = arrayOf(
        FrequencyRegularPayment.ONCE,
        FrequencyRegularPayment.DAILY,
        FrequencyRegularPayment.WEEKLY,
        FrequencyRegularPayment.MONTHLY,
        FrequencyRegularPayment.EVERY_YEAR,
    )

    private var selectedStartDate: String = ""
    private var selectedEndDate: String = ""
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditRegularPaymentBinding.inflate(inflater, container, false)
        val view: View = binding.root

        mRegularPaymentsViewModel = ViewModelProvider(this)[RegularPaymentsViewModel::class.java]


        mRegularPaymentsViewModel.readAllData.observe(
            viewLifecycleOwner
        ) { regularPayments ->
//            adapter.setData(regularPayments)
        }

        var accountsRegularPayment = mRegularPaymentsViewModel.readAllAccounts.value?.toTypedArray()
        mRegularPaymentsViewModel.readAllAccounts.observe(
            viewLifecycleOwner
        ) { regularPayments ->
            accountsRegularPayment = regularPayments.toTypedArray()
        }
        var categoriesRegularPayment =
            mRegularPaymentsViewModel.readAllCategories.value?.toTypedArray()
        mRegularPaymentsViewModel.readAllCategories.observe(
            viewLifecycleOwner
        ) { categories ->
            categoriesRegularPayment = categories.toTypedArray()
        }

        //spinner select type
        val selectTypeRegularPaymentSpinner = binding.selectType
        val selectTypeAdapter = this.context?.let {
            ArrayAdapter(
                it,
                R.layout.spiner_item,
                typesRegularPayment
            )
        }
        selectTypeRegularPaymentSpinner.adapter = selectTypeAdapter

        //spinner frequency type
        val selectFrequencyRegularPaymentSpinner = binding.selectFrequency
        val selectFrequencyAdapter = this.context?.let {
            ArrayAdapter(
                it,
                R.layout.spiner_item,
                frequenciesRegularPayment
            )
        }
        selectFrequencyRegularPaymentSpinner.adapter = selectFrequencyAdapter

        //spiner select account
        val selectAccountRegularPaymentSpinner = binding.selectAccount
        val selectAccountAdapter = this.context?.let {
            accountsRegularPayment?.let { it1 ->
                ArrayAdapter(
                    it,
                    R.layout.spiner_item,
                    it1.map { a -> a.name }
                )
            }
        }
        selectAccountRegularPaymentSpinner.adapter = selectAccountAdapter

        // go to create new account
        val addAccountButton = binding.addAccount
        addAccountButton.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.createAccountFragment)
        }

        //start date date_picker
        val textStartDate = binding.startDateText
        val dateStartPickerButton = binding.openStrtDatePicker
        dateStartPickerButton.setOnClickListener() {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager
            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    if (date != null) {
                        selectedStartDate = date
                        textStartDate.setText(selectedStartDate)
                    }
                }
            }

            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }

        //end date date_picker
        val dateEndPickerText = binding.endDateText
        val dateEndPickerButton = binding.openEndDatePicker
        dateEndPickerButton.setOnClickListener() {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    if (date != null) {
                        selectedEndDate = date
                        dateEndPickerText.setText(selectedEndDate)
                    }
                }
            }

            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }

        //categories recycle view
        categoriesAdapter = CategorySmallCardsAdapter()
        categoriesAdapter.setData(categoriesRegularPayment)
        val recyclerViewCategories = binding.categories
        recyclerViewCategories.adapter = categoriesAdapter
        recyclerViewCategories.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewCategories.itemAnimator = DefaultItemAnimator()

        // go to create new category
        val addCategoryButton = binding.addCategory
        addCategoryButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.createCategoryFragment)
        }

        // create button
        val addPaymentButton = binding.createButton
        addPaymentButton.setOnClickListener() {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val typePosition = binding.selectType.selectedItemPosition
        val amount = binding.amountText.text.toString().trim()
        val name = binding.paymentNameText.text.toString().trim()
        val accountPosition = binding.selectAccount.selectedItemPosition
        val frequency = binding.selectFrequency.selectedItemPosition
        val startDate = binding.startDateText.text.toString().trim()
        val endDate = binding.endDateText.text.toString().trim()
        val category = categoriesAdapter.getSelectedCategory()

        if (!inputCheckIsEmpty(amount)) {
            Toast.makeText(
                requireContext(),
                "Please fill in the field for the amount.",
                Toast.LENGTH_LONG
            )
                .show()
        } else if (!inputCheckIsNumber(amount)) {
            Toast.makeText(
                requireContext(),
                "PPlease fill in the field for the amount in numbers.",
                Toast.LENGTH_LONG
            )
                .show()
        } else if (!inputCheckIsEmpty(name)) {
            Toast.makeText(
                requireContext(),
                "Please fill in the field for the name.",
                Toast.LENGTH_LONG
            )
                .show()
        } else if (!inputCheckIsEmpty(startDate) && !inputCheckIsEmpty(endDate)) {
            Toast.makeText(
                requireContext(),
                "Please fill in the field for the dates.",
                Toast.LENGTH_LONG
            )
                .show()
        } else if (category == null) {
            Toast.makeText(
                requireContext(),
                "Please fill select category.",
                Toast.LENGTH_LONG
            )
                .show()
        } else if (!checkDates(startDate, endDate)) {
            Toast.makeText(
                requireContext(),
                "The start date cannot be later than the end date.",
                Toast.LENGTH_LONG
            )
                .show()
        } else {

            val regularPayment = RegularPaymentModel(
                id = 0,
                name = name,
                amount = amount.toDouble(),
                type = if (typePosition == 0) TransactionType.EXPENSES else TransactionType.INCOME,
                account = mRegularPaymentsViewModel.readAllAccounts.value?.get(accountPosition),
                category = category,
                frequency = frequenciesRegularPayment[frequency],
                startDate = LocalDate.parse(startDate, formatter),
                endDate = LocalDate.parse(endDate, formatter),
                isActive = true
            )
            mRegularPaymentsViewModel.addRegularPayment(regularPayment)
            Toast.makeText(requireContext(), "Regular payment successfully added!", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.nav_regular_payments)
        }
    }

    private fun inputCheckIsEmpty(amount: String) = !TextUtils.isEmpty(amount)

    private fun inputCheckIsNumber(amount: String) = amount.isDigitsOnly()

    private fun checkDates(startDate: String, endDate: String): Boolean {
        val startDateLD = LocalDate.parse(startDate, formatter)
        val endDateLD = LocalDate.parse(endDate, formatter)
        return startDateLD.isBefore(endDateLD)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
