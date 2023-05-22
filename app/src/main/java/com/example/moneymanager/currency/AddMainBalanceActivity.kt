package com.example.moneymanager.currency

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moneymanager.ui.NavigationActivity
import com.example.sp_v2.databinding.ActivityAddMainAccountBinding

class AddMainBalanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMainAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMainAccountBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val buttonBack = binding.backButton
        val buttonNext = binding.nextButton

        buttonBack.setOnClickListener {
            val intent = Intent(this, SelectCurrencyActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonNext.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}