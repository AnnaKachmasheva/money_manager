package com.example.moneymanager.ui.accounts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.data.AppDatabase
import com.example.moneymanager.data.repository.AccountRepository
import com.example.moneymanager.model.AccountModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class AccountsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<AccountModel>>
    private val repository: AccountRepository

    init {
        val userDao = AppDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addAccount(accountModel: AccountModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(accountModel)
        }
    }
}