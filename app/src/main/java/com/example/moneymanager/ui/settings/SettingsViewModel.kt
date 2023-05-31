package com.example.moneymanager.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moneymanager.data.AppDatabase
import com.example.moneymanager.data.repository.AccountRepository
import com.example.moneymanager.model.AccountModel

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<AccountModel>>
    private val repository: AccountRepository

    init {
        val userDao = AppDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(userDao)
        readAllData = repository.readAllData
    }

}