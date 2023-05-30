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

            transfer.accountFrom?.let {
                transfer.accountTo?.let { it1 ->
                    updateAccountsAfterDeleteTransaction(
                        it,
                        it1, transfer.amount
                    )
                }
            }
        }
    }

    private suspend fun updateAccountsAfterDeleteTransaction(
        accountFrom: AccountModel,
        accountTo: AccountModel,
        amount: Double
    ) {
        accountFrom.plusAmount(amount)
        repositoryAccount.updateAccount(accountFrom)

        accountTo.minusAmount(amount)
        repositoryAccount.updateAccount(accountTo)
    }

    fun updateTransfer(transferNew: TransferModel, transferOld: TransferModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTransfer(transferNew)
            updateAccountsAfterUpdateTransaction(transferNew, transferOld)
        }
    }

    private fun updateAccountsAfterUpdateTransaction(
        transferNew: TransferModel,
        transferOld: TransferModel
    ) {
        val oldAccountFrom = transferOld.accountFrom
        val oldAccountTo = transferOld.accountTo
        val amountOld = transferOld.amount

        oldAccountFrom?.plusAmount(amountOld)
        oldAccountTo?.minusAmount(amountOld)

        updateAccount(oldAccountFrom)
        updateAccount(oldAccountTo)

        val newAccountFrom = transferNew.accountFrom
        val newAccountTo = transferNew.accountTo
        val newAmount = transferNew.amount

        newAccountFrom?.minusAmount(newAmount)
        newAccountTo?.plusAmount(newAmount)

        updateAccount(newAccountFrom)
        updateAccount(newAccountTo)
    }


}