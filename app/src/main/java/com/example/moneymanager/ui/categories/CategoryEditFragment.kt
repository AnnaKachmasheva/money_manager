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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.ColorsAdapter
import com.example.moneymanager.adapter.IconsAdapter
import com.example.moneymanager.model.CategoryModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditCategoryBinding

class CategoryEditFragment : Fragment() {

    private var _binding: FragmentEditCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCategoriesViewModel: CategoriesViewModel
    private lateinit var mColorsAdapter: ColorsAdapter
    private lateinit var mIconsAdapter: IconsAdapter

    private val args by navArgs<CategoryEditFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditCategoryBinding.inflate(inflater, container, false)

        // set old data
        binding.nameText.setText(args.categoryModel.name)

        mColorsAdapter = ColorsAdapter()
        val recyclerViewColors = binding.colors
        recyclerViewColors.adapter = mColorsAdapter
        recyclerViewColors.layoutManager = GridLayoutManager(requireContext(), 5)
        recyclerViewColors.itemAnimator = DefaultItemAnimator()
        args.categoryModel.color?.let { mColorsAdapter.setInitPosition(it) }

        mIconsAdapter = IconsAdapter()
        val recyclerViewIcons = binding.icons
        recyclerViewIcons.adapter = mIconsAdapter
        recyclerViewIcons.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerViewIcons.itemAnimator = DefaultItemAnimator()
        args.categoryModel.icon.let { mIconsAdapter.setInitPosition(it) }

        mCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        val button = binding.createButton
        button.text = R.string.save.toString()
        button.setOnClickListener {
            updateDataToDatabase()
        }

        return binding.root
    }

    private fun updateDataToDatabase() {
        val name = binding.nameText.text.toString().trim()
        val icon = mIconsAdapter.getSelectedIcon()
        val color = mColorsAdapter.getSelectedColor()

        if (inputCheck(name)) {
            val category = CategoryModel(
                id = args.categoryModel.id,
                name = name,
                icon = icon,
                color = color
            )
            mCategoriesViewModel.updateCategory(category)
            Toast.makeText(requireContext(), "Category successfully updated!", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_categoryEditFragment_to_nav_categories)
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