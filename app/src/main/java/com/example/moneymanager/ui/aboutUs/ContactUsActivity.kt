package com.example.moneymanager.ui.aboutUs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sp_v2.databinding.ActivityContactUsBinding

class ContactUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactUsBinding

//    private lateinit var mSubject: String
//    private lateinit var mMessage: String
//    private lateinit var mEmail: String

    @SuppressLint("AppCompatMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val button = binding.contactUsButton


        //todo action
        button.setOnClickListener {

//            print()
//            val intent = Intent(this, SelectCurrencyActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }
}