package com.example.moneymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.dao.TransactionDao
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.model.TransferModel

class TransactionRepository(private val transactionDao: TransactionDao) {

    val readAllDataExpenses: LiveData<List<ExpensesIncomeModel>> =
        transactionDao.readAllDataExpenses()
    val readAllDataIncome: LiveData<List<ExpensesIncomeModel>> = transactionDao.readAllDataIncome()
    val readAllDataTransfer: LiveData<List<TransferModel>> = transactionDao.readAllDataTransfer()

    val totalTransferAmount: LiveData<Double> = transactionDao.getTotalTransferAmount()
    val totalExpencesAmount: LiveData<Double> = transactionDao.getTotalExpencesAmount()
    val totalIncomeAmount: LiveData<Double> = transactionDao.getTotalIncomeAmount()

    suspend fun addExpensesIncome(expensesIncomeModel: ExpensesIncomeModel) {
        transactionDao.insertExpensesIncome(expensesIncomeModel)
    }


    //transfers

    suspend fun addTransfer(transferModel: TransferModel) {
        transactionDao.insertTransfer(transferModel)
    }

    suspend fun deleteTransfer(transferModel: TransferModel) {
        transactionDao.deleteTransfer(transferModel)
    }

    suspend fun updateTransfer(transferModel: TransferModel) {
        transactionDao.updateTransfer(transferModel)
    }


    // expenses
    fun getExpensesByCategoryId(id: Int?) : LiveData<List<ExpensesIncomeModel>> {
        return transactionDao.getExpensesByCategoryId(id)
    }

    suspend fun deleteTransaction(model: ExpensesIncomeModel) {
        transactionDao.deleteTransaction(model)
    }

    suspend fun updateTransaction(model: ExpensesIncomeModel) {
        transactionDao.updateTransaction(model)
    }

}