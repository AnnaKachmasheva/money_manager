package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.CategorySmallCardsAdapter
import com.example.moneymanager.adapter.DateCardsAdapter
import com.example.moneymanager.model.DateModel
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.model.enums.TransactionType
import com.example.moneymanager.utils.DateItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditExpensesIncomeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EditIncomeFragment : Fragment() {

    private var _binding: FragmentEditExpensesIncomeBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: String = ""
    private lateinit var dateCardAdapter: DateCardsAdapter
    private lateinit var categoriesAdapter: CategorySmallCardsAdapter
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var oldModel: ExpensesIncomeModel

    private val args by navArgs<EditExpensesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditExpensesIncomeBinding.inflate(inflater, container, false)
        val view = binding.root

        mHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        initAccountsData()
        initCategoriesData()

        // set old data
        oldModel = args.transactionModel
        binding.amountText.setText(oldModel.amount.toString())
        binding.noteText.setText(oldModel.note)

        var accountsRegularPayment = mHomeViewModel.readAllAccounts.value?.toTypedArray()
        mHomeViewModel.readAllAccounts.observe(
            viewLifecycleOwner
        ) { regularPayments ->
            accountsRegularPayment = regularPayments.toTypedArray()
        }
        var categoriesRegularPayment =
            mHomeViewModel.readAllCategories.value?.toTypedArray()
        mHomeViewModel.readAllCategories.observe(
            viewLifecycleOwner
        ) { categories ->
            categoriesRegularPayment = categories.toTypedArray()
        }

        //date picker
        val datePickerButton = binding.openDatePicker
        datePickerButton.setOnClickListener() {
            //todo open dialog with date picker

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

        val addAccountButton = binding.addAccount
        addAccountButton.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.createAccountFragment)
        }

        val addCategoryButton = binding.addCategory
        addCategoryButton.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.createCategoryFragment)
        }

        val addPhotoButton = binding.addPhoto
        addPhotoButton.setOnClickListener() {
            //todo open dialog for load photo
        }

        // create button
        val addPaymentButton = binding.createButton
        addPaymentButton.text = "Save"
        addPaymentButton.setOnClickListener() {
            updateData()
        }

        return view
    }


    private fun initAccountsData() {
        val accounts = this@EditIncomeFragment.context?.let {
            ArrayAdapter<Any>(
                it,
                android.R.layout.simple_spinner_item
            )
        }
        mHomeViewModel.readAllAccounts.observe(viewLifecycleOwner) { accountList ->
            accountList?.forEach {
                accounts?.add(it.name)
            }
            accounts?.getPosition(args.transactionModel.account?.name)
                ?.let { binding.selectAccountSpiner.setSelection(it) }
        }
        binding.selectAccountSpiner.adapter = accounts
    }

    private fun initCategoriesData() {
        categoriesAdapter = CategorySmallCardsAdapter()
        val recyclerViewCategories = binding.categories
        mHomeViewModel.readAllCategories.observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setData(categories.toTypedArray())
            args.transactionModel.category?.let { categoriesAdapter.setInitPosition(it) }
        }
        recyclerViewCategories.adapter = categoriesAdapter
        recyclerViewCategories.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewCategories.itemAnimator = DefaultItemAnimator()
    }

    private fun updateData() {
        val amount = binding.amountText.text.toString().trim()
        val accountPosition = binding.selectAccountSpiner.selectedItemPosition
        val category = categoriesAdapter.getSelectedCategory()
        val date = dateCardAdapter.getSelectedDate()
        val note = binding.noteText.text.toString().trim()

        if (amount.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please enter the amount as a number.",
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            val amountTransfer = amount.toDouble()
            val accountModel = mHomeViewModel.readAllAccounts.value?.get(accountPosition)
            accountModel?.plusAmount(amountTransfer)

            val transaction = ExpensesIncomeModel(
                id = args.transactionModel.id,
                type = TransactionType.INCOME,
                amount = amountTransfer,
                date = date.date,
                note = note,
                account = accountModel,
                category = category
            )
            //insert transaction
            mHomeViewModel.updateTransaction(transaction, oldModel)
            //update account
            mHomeViewModel.updateAccount(accountModel)
            Toast.makeText(requireContext(), "Transaction successfully updated!", Toast.LENGTH_LONG)
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