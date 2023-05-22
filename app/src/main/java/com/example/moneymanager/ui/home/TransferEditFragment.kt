package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.DateCardsAdapter
import com.example.moneymanager.model.DateModel
import com.example.moneymanager.utils.DateItems
import com.example.sp_v2.databinding.FragmentEditTransferBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TransferEditFragment : Fragment() {
    private var _binding: FragmentEditTransferBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: String = ""
    private lateinit var dateCardAdapter: DateCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTransferBinding.inflate(inflater, container, false)
        val view = binding.root

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

        return view
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