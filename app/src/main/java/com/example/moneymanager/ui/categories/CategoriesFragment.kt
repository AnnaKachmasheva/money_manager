package com.example.moneymanager.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.adapter.CategoryCardsAdapter
import com.example.moneymanager.utils.CategoriesItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountsViewModel =
            ViewModelProvider(this)[CategoriesViewModel::class.java]

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view: View = binding.root

        inflater.inflate(R.layout.fragment_categories, container, false)

        val createButton = binding.addButton
        createButton.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.editCategoryFragment)
        }

        return view
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding.categories.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = CategoryCardsAdapter(CategoriesItems.CategoriesItems)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}