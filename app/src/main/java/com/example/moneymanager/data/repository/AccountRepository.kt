package com.example.moneymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.dao.AccountDao
import com.example.moneymanager.model.AccountModel

class AccountRepository(private val accountDao: AccountDao) {

    val readAllData: LiveData<List<AccountModel>> = accountDao.readAllData()

    suspend fun addAccount(accountModel: AccountModel) {
        accountDao.insert(accountModel)
    }

}