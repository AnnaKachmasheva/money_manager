package com.example.moneymanager.ui.aboutUs

import android.annotation.SuppressLint
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

    @SuppressLint("CommitTransaction")
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

        val contactUsFragment = ContactUsFragment()

        button.setOnClickListener() {

            //todo -> contact us
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_about_us, contactUsFragment)?.commit()
//            Navigation.findNavController(view)
//                .navigate(R.id.contactUsFragment)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}