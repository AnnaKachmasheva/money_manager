package com.example.moneymanager.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.CategorySmallCardsAdapter
import com.example.moneymanager.adapter.DateCardsAdapter
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.model.DateModel
import com.example.moneymanager.utils.CategoriesItems
import com.example.moneymanager.utils.DateItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditTransactionBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EditTransactionFragment : Fragment() {

    private var _binding: FragmentEditTransactionBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: String = ""
    private lateinit var dateCardAdapter: DateCardsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTransactionBinding.inflate(inflater, container, false)
        val view: View = binding.root

        inflater.inflate(R.layout.fragment_edit_transaction, container, false);

        val addAccountButton = binding.addAccount
        addAccountButton.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.createAccountFragment)
        }

        val addCategoryButton = binding.addCategory
        addCategoryButton.setOnClickListener() {
            //todo open dialog for selecting category
        }

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
                        dateCardAdapter.notifyDataSetChanged()
                    }
                }
            }

            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }

        val addPhotoButton = binding.addPhoto
        addPhotoButton.setOnClickListener() {
            //todo open dialog for load photo
        }

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        _binding?.days?.apply {
            layoutManager = GridLayoutManager(activity, 3)
            dateCardAdapter = DateCardsAdapter(DateItems.DateItems)
            adapter = dateCardAdapter
        }

        _binding?.categories?.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter =
                CategorySmallCardsAdapter(
                    CategoriesItems.CategoriesItems.take(3)
                            as ArrayList<CategoryModel>
                )
        }
    }


}