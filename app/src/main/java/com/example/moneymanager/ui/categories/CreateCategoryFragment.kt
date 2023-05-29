package com.example.moneymanager.ui.categories

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.moneymanager.adapter.ColorsAdapter
import com.example.moneymanager.adapter.IconsAdapter
import com.example.moneymanager.model.CategoryModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditCategoryBinding

class CreateCategoryFragment : Fragment() {

    private var _binding: FragmentEditCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCategoriesViewModel: CategoriesViewModel
    private lateinit var mColorsAdapter: ColorsAdapter
    private lateinit var mIconsAdapter: IconsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditCategoryBinding.inflate(inflater, container, false)


        mColorsAdapter = ColorsAdapter()
        val recyclerViewColors = binding.colors
        recyclerViewColors.adapter = mColorsAdapter
        recyclerViewColors.itemAnimator = DefaultItemAnimator()

        mIconsAdapter = IconsAdapter()
        val recyclerViewIcons = binding.icons
        recyclerViewIcons.adapter = mColorsAdapter
        recyclerViewIcons.itemAnimator = DefaultItemAnimator()

        mCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        binding.createButton.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = binding.nameText.toString().trim()
        val icon = mIconsAdapter.getSelectedIcon()
        val color = mColorsAdapter.getSelectedColor()

        if (inputCheck(name)) {
            val category = CategoryModel(
                id = 0,
                name = name,
                icon = icon,
                color = color
            )
            mCategoriesViewModel.addCategory(category)
            Toast.makeText(requireContext(), "Category successfully added!", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_createAccountFragment_to_nav_accounts)
        } else {
            Toast.makeText(requireContext(), "Please fill name field.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(name: String): Boolean {
        return !TextUtils.isEmpty(name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}