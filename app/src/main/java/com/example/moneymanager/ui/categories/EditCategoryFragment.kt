package com.example.moneymanager.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moneymanager.adapter.ColorsAdapter
import com.example.moneymanager.adapter.IconsAdapter
import com.example.moneymanager.utils.ColorItems
import com.example.moneymanager.utils.IconItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentEditCategoryBinding

class EditCategoryFragment : Fragment() {

    private var _binding: FragmentEditCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var mName: String
    private lateinit var mIcon: String
//    private lateinit var mColor: Int


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditCategoryBinding.inflate(inflater, container, false)
        val view: View = binding.root

        inflater.inflate(R.layout.fragment_edit_category, container, false)

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        binding.colors.apply {
            layoutManager = GridLayoutManager(activity, 5)
            adapter = ColorsAdapter(ColorItems.ColorItems)
        }

        binding.icons.apply {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = IconsAdapter(IconItems.IconItems)
        }


    }


}