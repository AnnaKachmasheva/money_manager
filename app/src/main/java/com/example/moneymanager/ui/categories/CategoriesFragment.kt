package com.example.moneymanager.ui.categories

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.CategoryCardsAdapter
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.ui.accounts.AccountFragmentDirections
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCategoriesViewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        mCategoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        val adapter = CategoryCardsAdapter(onClickListener = this::openDialog)
        val recyclerView = binding.categoriesRecycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.itemAnimator = DefaultItemAnimator()


        mCategoriesViewModel.readAllData.observe(viewLifecycleOwner, Observer { category ->
            adapter.setData(category)
        })

        val addButton = binding.addButton
        addButton.setOnClickListener() {
            findNavController().navigate(R.id.action_nav_categories_to_createCategoryFragment)
        }

        return view
    }

    private fun openDialog(view: View, categoryModel: CategoryModel) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(categoryModel.name)
        alertDialogBuilder.setMessage("Select an operation")
//        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton(
            "Update"
        ) { dialog, which ->
            val action = CategoriesFragmentDirections.actionNavCategoriesToCategoryEditFragment2(categoryModel)
            findNavController().navigate(action)
            dialog.cancel()
        }
        alertDialogBuilder.setNegativeButton(
            "Delete"
        ) { dialog, which ->
            deleteDataFromDatabase(categoryModel)
            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun deleteDataFromDatabase(categoryModel: CategoryModel) {
        mCategoriesViewModel.deleteCategory(categoryModel)
        Toast.makeText(requireContext(), "Account successfully deleted!", Toast.LENGTH_LONG)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}