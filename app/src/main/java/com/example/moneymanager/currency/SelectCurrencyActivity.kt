package com.example.moneymanager.currency

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.adapter.CurrenciesListAdapter
import com.example.moneymanager.model.CurrencyModel
import com.example.moneymanager.ui.NavigationActivity
import com.example.moneymanager.utils.CurrencyUtils
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentSelectDefaultCurrencyBinding

class SelectCurrencyActivity : AppCompatActivity() {

    private lateinit var binding: FragmentSelectDefaultCurrencyBinding
    private lateinit var adapter: CurrenciesListAdapter

    //todo need fix
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentSelectDefaultCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CurrencyUtils.loadInitCurrencies(this.application)
        val listData: ArrayList<CurrencyModel> = CurrencyUtils.CurrenciesList

        val recyclerview = findViewById<RecyclerView>(R.id.currenciesList)
        recyclerview.adapter = CurrenciesListAdapter(listData)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val button = binding.nextButton
        button.setOnClickListener {
            val intent = Intent(this, SelectCurrencyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}