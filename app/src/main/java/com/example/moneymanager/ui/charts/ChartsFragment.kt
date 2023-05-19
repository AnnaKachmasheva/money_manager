package com.example.moneymanager.ui.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sp_v2.databinding.FragmentChartsBinding

class ChartsFragment : Fragment() {

    private var _binding: FragmentChartsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountsViewModel =
            ViewModelProvider(this)[ChartsViewModel::class.java]

        _binding = FragmentChartsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCharts
        accountsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}