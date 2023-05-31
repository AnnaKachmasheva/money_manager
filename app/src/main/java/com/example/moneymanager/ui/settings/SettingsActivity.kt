package com.example.moneymanager.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sp_v2.R
import com.example.sp_v2.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, SettingsFragment())
            .commit()
    }

}