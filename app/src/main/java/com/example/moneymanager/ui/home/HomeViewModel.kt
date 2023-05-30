package com.example.moneymanager.ui.home

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

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val readAllDataExpenses: LiveData<List<ExpensesIncomeModel>>
    val readAllDataIncome: LiveData<List<ExpensesIncomeModel>>
    val readAllDataTransfer: LiveData<List<TransferModel>>
    val readAllAccounts: LiveData<List<AccountModel>>

    val totalTransferAmount: LiveData<Double>


    private val repository: TransactionRepository
    private val repositoryAccount: AccountRepository

    init {
        val transactionDao = AppDatabase.getDatabase(application).transactionDao()
        val accountDao = AppDatabase.getDatabase(application).accountDao()

        repository = TransactionRepository(transactionDao)
        readAllDataExpenses = repository.readAllDataExpenses
        readAllDataIncome = repository.readAllDataIncome
        readAllDataTransfer = repository.readAllDataTransfer

        totalTransferAmount = repository.totalTransferAmount

        repositoryAccount = AccountRepository(accountDao)
        readAllAccounts = repositoryAccount.readAllData
    }

    fun addExpensesIncome(expensesIncomeModel: ExpensesIncomeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpensesIncome(expensesIncomeModel)
        }
    }

    fun addTransfer(transferModel: TransferModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransfer(transferModel)
        }
    }

    fun updateAccount(account: AccountModel?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (account != null) {
                repositoryAccount.updateAccount(account)
            }
        }
    }

    fun deleteTransfer(transfer: TransferModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransfer(transfer)

            transfer.accountFrom?.plusAmount(transfer.amount)
            transfer.accountFrom?.let { repositoryAccount.updateAccount(it) }

            transfer.accountTo?.minusAmount(transfer.amount)
            transfer.accountTo?.let { repositoryAccount.updateAccount(it) }
        }
    }

    fun updateTransfer(
        transfer: TransferModel,
        transferOldAmountFrom: Double,
        transferOldAmountTo: Double,
        oldAmount: Double
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTransfer(transfer)

            val accountFrom = transfer.accountFrom
            accountFrom?.plusAmount(transferOldAmountFrom + oldAmount - transfer.amount)
            if (accountFrom != null) {
                repositoryAccount.updateAccount(accountFrom)
            }

            val accountTo = transfer.accountTo
            accountTo?.plusAmount(transferOldAmountTo - oldAmount + transfer.amount)
            if (accountTo != null) {
                repositoryAccount.updateAccount(accountTo)
            }

        }
    }
}