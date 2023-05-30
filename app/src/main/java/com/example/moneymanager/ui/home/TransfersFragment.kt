package com.example.moneymanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentTransfersBinding


class TransfersFragment : Fragment() {
    private var _binding: FragmentTransfersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransfersBinding.inflate(inflater, container, false)
        val view = binding.root

        val button = binding.addButton
        button.setOnClickListener() {
//            findNavController().navigate(R.id.action_transfersFragment_to_createTransferFragment)
            Navigation.findNavController(view)
                .navigate(R.id.createTransferFragment)
        }

        return view
    }
}