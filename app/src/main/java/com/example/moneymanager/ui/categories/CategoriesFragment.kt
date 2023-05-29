package com.example.moneymanager.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.adapter.CategoryCardsAdapter
import com.example.moneymanager.main.MoneyManagerApp
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
//
//    private val categoriesViewModel: CategoriesViewModel by viewModels {
//        CategoriesViewModel.CategoryViewModelFactory(
//            (requireActivity().applicationContext as MoneyManagerApp).repositoryCategory
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view: View = binding.root
//        inflater.inflate(R.layout.fragment_categories, container, false)

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        recyclerView = binding.categoriesRecycleView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
//        val categoriesAdapter = CategoryCardsAdapter(categoriesViewModel.categoriesListLiveData)


        // goto edit category fragment
        val createButton = binding.addButton
        createButton.setOnClickListener()
        {
            Navigation.findNavController(binding.root)
                .navigate(R.id.editCategoryFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}