package com.example.moneymanager.ui.regularPayments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sp_v2.databinding.FragmentRegularPaymentsBinding

class RegularPaymentsFragment : Fragment() {

    private var _binding: FragmentRegularPaymentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[RegularPaymentsViewModel::class.java]

        _binding = FragmentRegularPaymentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textRegularPayments
//        profileViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}