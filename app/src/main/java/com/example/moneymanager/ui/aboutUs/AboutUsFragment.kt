package com.example.moneymanager.ui.aboutUs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutViewModel =
            ViewModelProvider(this)[AboutUsViewModel::class.java]

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val view: View = binding.root


        val button = binding.contactUsButton

        button.setOnClickListener() {
            Navigation.findNavController(view)
                .navigate(R.id.contactUsFragment)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}