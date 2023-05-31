package com.example.moneymanager.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moneymanager.ui.NavigationActivity
import com.example.sp_v2.R
import com.example.sp_v2.databinding.ActivitySplashscreenBinding

class MainSplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = binding.mainImage
        image.setImageResource(R.drawable.splashscreen_image)

        val button = binding.startButton
        button.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}