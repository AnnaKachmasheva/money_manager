package com.example.moneymanager.currency

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.ui.NavigationActivity
import com.example.moneymanager.adapter.CurrenciesListAdapter
import com.example.moneymanager.model.CurrencyModel
import com.example.moneymanager.utils.CurrencyUtils
import com.example.sp_v2.R
import com.example.sp_v2.databinding.ActivitySelectCurrencyBinding

class SelectCurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectCurrencyBinding
    private lateinit var adapter: CurrenciesListAdapter

    private val currenciesViewModel: CurrenciesViewModel = CurrenciesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CurrencyUtils.loadInitCurrencies(this.application)
        val listData: List<CurrencyModel> = CurrencyUtils.CurrenciesList

        adapter = CurrenciesListAdapter { currencyModel -> adapterOnClick(currencyModel) }

        val recyclerview = findViewById<RecyclerView>(R.id.currenciesList)
        recyclerview.adapter = adapter

        currenciesViewModel.currencyListLiveData.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }

        val button = binding.nextButton
//        val inputSearch = binding.searchText
//        val recycleView = binding.currenciesList

        button.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun adapterOnClick(currencyModel: CurrencyModel) {
//        val intent = Intent(this, FlowerDetailActivity()::class.java)
//        intent.putExtra(FLOWER_ID, flower.id)
        startActivity(intent)
    }


}