package com.example.moneymanager.ui.aboutUs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sp_v2.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutViewModel =
            ViewModelProvider(this)[AboutUsViewModel::class.java]

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val button = binding.contactUsButton
        button.setOnClickListener {
            val intent = Intent(activity, ContactUsActivity::class.java)
            startActivity(intent)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}