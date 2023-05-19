package com.example.moneymanager.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.moneymanager.currency.SelectCurrencyActivity
import com.example.sp_v2.R
import com.example.sp_v2.databinding.ActivitySplashscreenBinding

class MainSplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val button = binding.startButton

        button.setOnClickListener {
            val intent = Intent(this, SelectCurrencyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}