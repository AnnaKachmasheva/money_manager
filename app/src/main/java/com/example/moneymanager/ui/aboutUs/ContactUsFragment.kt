package com.example.moneymanager.ui.aboutUs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sp_v2.databinding.FragmentContactUsBinding

class ContactUsFragment : Fragment() {

    private var _binding: FragmentContactUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactUsBinding.inflate(inflater, container, false)

        val view = binding.root
        val sendButton = binding.sendButton
        sendButton.setOnClickListener() {
            val intent = Intent(Intent.ACTION_SEND)

            val subject = binding.subjectText.text.toString()
            val message = binding.messageText.text.toString()
            val email = binding.emailText.text.toString()

            val recipientMail = "annakachmasheva@gmail.com"
            intent.data = Uri.parse("mailto:$recipientMail")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            startActivity(Intent.createChooser(intent, "Select your Email app"))
        }

        return view
    }

}