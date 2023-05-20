package com.example.moneymanager.ui.aboutUs

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentContactUsBinding

class ContactUsFragment : Fragment() {

    private var _binding: FragmentContactUsBinding? = null
    private val binding get() = _binding!!

//    private var mSubject: String = ""
//    private var mMessage: String = ""
//    private var mEmail: String = ""

//    companion object {
//        @JvmStatic
//        fun newInstance(subject: String, message: String, email: String): ContactUsFragment {
//            return ContactUsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(EDITOR_SUBJECT, subject)
//                    putString(EDITOR_MESSAGE, message)
//                    putString(EDITOR_EMAIL, email)
//                }
//            }
//        }

//        const val EDITOR_SUBJECT = "subject"
//        const val EDITOR_MESSAGE = "message"
//        const val EDITOR_EMAIL = "email"

//        const val PROFILE_EDITOR_FRAGMENT_REQUEST_KEY = "PROFILE_EDITOR_FRAGMENT_REQUEST_KEY"
//    }

//    private fun setInitialValues(subject: String, message: String, email: String) {
//        mSubject = subject
//        mMessage = message
//        mEmail = email
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        arguments?.let {
//            setInitialValues(
//                it.getString(EDITOR_SUBJECT, ""),
//                it.getString(EDITOR_MESSAGE, ""),
//                it.getString(EDITOR_EMAIL, "")
//            )
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactUsBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_contact_us, container, false);


//        binding.subjectText.setText(mSubject)
//        binding.messageText.setText(mMessage)
//        binding.emailText.setText(mEmail)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sendButton = binding.sendButton
        sendButton.setOnClickListener() {
//            launchMyAlertDialog()
        }

    }

//    fun launchMyAlertDialog() {
//        AlertDialog.Builder(activity)
//            .setMessage("My Dialog message")
//            .setPositiveButton("Positive") { _, _ -> onDialogPostiveCLick() }
//            .setCancelable(false)
//            .create().show()
//    }
//
//    fun onDialogPostiveCLick() {
//        Log.i(TAG, "Listener returns a postive click")
//    }



//    todo check why not working????
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        val navController = Navigation.findNavController(view)
//
//        navController.navigateUp()
//
//
//    }

}