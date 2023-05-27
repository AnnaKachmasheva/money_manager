package com.example.moneymanager.main

import android.app.Application
import com.example.moneymanager.db.AppDatabase
import com.example.moneymanager.db.repository.AccountRepository
import com.example.moneymanager.db.repository.CategoryRepository
import com.example.moneymanager.utils.CurrencyUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MoneyManagerApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repositoryCategory by lazy { CategoryRepository(database.categoryDao()) }
    val repositoryAccount by lazy { AccountRepository(database.accountDao()) }

    override fun onCreate() {
        super.onCreate()
//        AppDatabase.getDatabase(this)

    }
}