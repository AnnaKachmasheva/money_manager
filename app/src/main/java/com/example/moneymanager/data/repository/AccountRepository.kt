package com.example.moneymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.dao.AccountDao
import com.example.moneymanager.model.AccountModel

class AccountRepository(private val accountDao: AccountDao) {

    val readAllData: LiveData<List<AccountModel>> = accountDao.readAllData()
    val totalAmount: LiveData<Double> = accountDao.getTotalAmount()

    suspend fun addAccount(accountModel: AccountModel) {
        accountDao.insert(accountModel)
    }

    suspend fun updateAccount(accountModel: AccountModel) {
        accountDao.update(accountModel)
    }

    suspend fun deleteAccount(accountModel: AccountModel) {
        accountDao.delete(accountModel)

    }

}