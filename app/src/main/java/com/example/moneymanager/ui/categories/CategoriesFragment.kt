package com.example.moneymanager.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.adapter.AccountsAdapter
import com.example.moneymanager.adapter.CategoryCardsAdapter
import com.example.moneymanager.main.MoneyManagerApp
import com.example.moneymanager.ui.accounts.AccountsViewModel
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAccountsBinding
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

        val adapter = CategoryCardsAdapter()
        val recyclerView = binding.categoriesRecycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.itemAnimator = DefaultItemAnimator()

        mCategoriesViewModel.readAllData.observe(viewLifecycleOwner, Observer { category ->
            adapter.setData(category)
        })

//        val addButton = binding.addButton
//        addButton.setOnClickListener() {
//            findNavController().navigate(R.id.)
//        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}