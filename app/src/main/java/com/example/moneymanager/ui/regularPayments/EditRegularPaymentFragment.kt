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
import androidx.navigation.fragment.navArgs
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


class EditRegularPaymentFragment : Fragment() {

    private var _binding: FragmentEditRegularPaymentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<RegularPaymentFragmentArgs>()

    private lateinit var categoriesAdapter: CategorySmallCardsAdapter
    private lateinit var mRegularPaymentsViewModel: RegularPaymentsViewModel
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditRegularPaymentBinding.inflate(inflater, container, false)
        val view: View = binding.root

        mRegularPaymentsViewModel = ViewModelProvider(this)[RegularPaymentsViewModel::class.java]
        initAccountsData()
        initCategoriesData()

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
        selectTypeRegularPaymentSpinner.setSelection(
            if (args.regularPayment.type == TransactionType.EXPENSES) 0 else 1
        )

        // set old name and amount
        binding.paymentNameText.setText(args.regularPayment.name)
        binding.amountText.setText(args.regularPayment.amount.toString())

        // spinner frequency type
        val selectFrequencyRegularPaymentSpinner = binding.selectFrequency
        val selectFrequencyAdapter = this.context?.let {
            ArrayAdapter(
                it,
                R.layout.spiner_item,
                frequenciesRegularPayment
            )
        }
        selectFrequencyRegularPaymentSpinner.adapter = selectFrequencyAdapter

        // go to create new account
        val addAccountButton = binding.addAccount
        addAccountButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.createAccountFragment)
        }

        //start date date_picker
        val textStartDate = binding.startDateText
        textStartDate.setText(args.regularPayment.startDate?.format(formatter) ?: "")
        val dateStartPickerButton = binding.openStrtDatePicker
        dateStartPickerButton.setOnClickListener {
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
        dateEndPickerText.setText(args.regularPayment.endDate?.format(formatter) ?: "")
        val dateEndPickerButton = binding.openEndDatePicker
        dateEndPickerButton.setOnClickListener {
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

        // go to create new category
        val addCategoryButton = binding.addCategory
        addCategoryButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.createCategoryFragment)
        }

        // create button
        val addPaymentButton = binding.createButton
        addPaymentButton.text = "Save"
        addPaymentButton.setOnClickListener {
            updateDataToDatabase()
        }

        return view
    }

    private fun initAccountsData() {
        val accounts = this@EditRegularPaymentFragment.context?.let {
            ArrayAdapter<Any>(
                it,
                android.R.layout.simple_spinner_item
            )
        }
        mRegularPaymentsViewModel.readAllAccounts.observe(viewLifecycleOwner) { accountsList ->
            accountsList?.forEach {
                accounts?.add(it.name)
            }
            accounts?.getPosition(args.regularPayment.account?.name)
                ?.let { binding.selectAccount.setSelection(it) }
        }
        binding.selectAccount.adapter = accounts
    }

    private fun initCategoriesData() {
        categoriesAdapter = CategorySmallCardsAdapter()
        val recyclerViewCategories = binding.categories
        mRegularPaymentsViewModel.readAllCategories.observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setData(categories.toTypedArray())
            args.regularPayment.category?.let { categoriesAdapter.setInitPosition(it) }
        }
        recyclerViewCategories.adapter = categoriesAdapter
        recyclerViewCategories.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewCategories.itemAnimator = DefaultItemAnimator()
    }


    private fun updateDataToDatabase() {
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
            ).show()
        } else if (category == null) {
            Toast.makeText(
                requireContext(),
                "Please fill select category.",
                Toast.LENGTH_LONG
            ).show()
        } else if (!checkDates(startDate, endDate)) {
            Toast.makeText(
                requireContext(),
                "The start date cannot be later than the end date.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val regularPayment = RegularPaymentModel(
                id = args.regularPayment.id,
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
            mRegularPaymentsViewModel.updateRegularPayment(regularPayment)
            Toast.makeText(
                requireContext(),
                "Regular payment successfully updated!",
                Toast.LENGTH_LONG
            )
                .show()
            val action =
                EditRegularPaymentFragmentDirections.actionEditRegularPaymentFragmentToRegularPaymentFragment(
                    regularPayment
                )
            findNavController().navigate(action)
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
