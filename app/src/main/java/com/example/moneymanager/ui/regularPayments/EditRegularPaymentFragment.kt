package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.CategorySmallCardsAdapter
import com.example.moneymanager.adapter.DateCardsAdapter
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.model.enums.FrequencyRegularPayment
import com.example.moneymanager.model.enums.TransactionType
import com.example.moneymanager.ui.home.DatePickerFragment
import com.example.moneymanager.utils.CategoriesItems
import com.example.moneymanager.utils.DateItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditRegularPaymentBinding
import java.time.LocalDate


class EditRegularPaymentFragment : Fragment() {

    private var _binding: FragmentEditRegularPaymentBinding? = null
    private val binding get() = _binding!!

    private val typesRegularPayment = arrayOf(
        TransactionType.EXPENSES.type,
        TransactionType.INCOME.type
    )

    private val frequenciesRegularPayment = arrayOf(
        FrequencyRegularPayment.ONCE.frequency,
        FrequencyRegularPayment.DAILY.frequency,
        FrequencyRegularPayment.WEEKLY.frequency,
        FrequencyRegularPayment.MONTHLY.frequency,
        FrequencyRegularPayment.EVERY_YEAR.frequency,
    )

    private var selectedStartDate: String = ""
    private var selectedEndDate: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountsViewModel =
            ViewModelProvider(this)[RegularPaymentsViewModel::class.java]

        _binding = FragmentEditRegularPaymentBinding.inflate(inflater, container, false)
        val view: View = binding.root

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

        // category button
        val addCategoryButton = binding.addCategory
        addCategoryButton.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editCategoryFragment)
        }

        return view
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        _binding?.categories?.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter =
                CategorySmallCardsAdapter(
                    CategoriesItems.CategoriesItems.take(3)
                            as ArrayList<CategoryModel>
                )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
