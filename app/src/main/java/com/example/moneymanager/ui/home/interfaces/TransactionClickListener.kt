package com.example.moneymanager.ui.home.interfaces

import com.example.moneymanager.model.CategoryModel

interface TransactionClickListener {

    fun onTransactionClickListener(expensesIncomeModel: Pair<CategoryModel?, Double>)

}