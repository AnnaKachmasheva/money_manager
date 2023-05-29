package com.example.moneymanager.main

import android.app.Application
import com.example.moneymanager.data.AppDatabase
import com.example.moneymanager.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MoneyManagerApp : Application() {
//
//    val applicationScope = CoroutineScope(SupervisorJob())
//    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
//    val accountRepository by lazy { AccountRepository(database.accountDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}