package com.example.moneymanager.ui.accounts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.data.AppDatabase
import com.example.moneymanager.data.repository.AccountRepository
import com.example.moneymanager.data.repository.TransactionRepository
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.model.TransferModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<AccountModel>>
    val totalAmount: LiveData<Double>
    private val repository: AccountRepository

    val readAllDataExpenses: LiveData<List<ExpensesIncomeModel>>
    val readAllDataIncome: LiveData<List<ExpensesIncomeModel>>
    val readAllDataTransfer: LiveData<List<TransferModel>>
    private val transactionRepository: TransactionRepository

    init {
        val userDao = AppDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(userDao)
        readAllData = repository.readAllData
        totalAmount = repository.totalAmount

        val transactionDao = AppDatabase.getDatabase(application).transactionDao()
        transactionRepository = TransactionRepository(transactionDao)
        readAllDataExpenses = transactionRepository.readAllDataExpenses
        readAllDataIncome = transactionRepository.readAllDataIncome
        readAllDataTransfer = transactionRepository.readAllDataTransfer
    }

    fun addAccount(accountModel: AccountModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(accountModel)
        }
    }

    fun updateAccount(accountModel: AccountModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAccount(accountModel)
        }
    }

    fun deleteAccount(accountModel: AccountModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAccount(accountModel)
        }
    }

}