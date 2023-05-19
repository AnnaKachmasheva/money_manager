package com.example.moneymanager.ui.categories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.adapter.CategoryCardsAdapter
import com.example.moneymanager.utils.CategoriesItems
import com.example.sp_v2.R
import com.example.sp_v2.databinding.FragmentCategoriesBinding

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val categories = CategoriesItems.CategoriesItems

        val adapter = CategoryCardsAdapter(categories)
        val layoutManager = LinearLayoutManager(this, GridLayoutManager.VERTICAL, false)

        val recycleView = findViewById<RecyclerView>(R.id.currenciesList)
        recycleView.layoutManager = layoutManager
        recycleView.adapter = adapter

        val buttonCreateCategory = binding.nextButton
        
        //todo add clickListener
    }

}