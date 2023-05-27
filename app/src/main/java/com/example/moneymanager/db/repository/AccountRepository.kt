package com.example.moneymanager.db.repository

import androidx.annotation.WorkerThread
import com.example.moneymanager.db.dao.AccountDao
import com.example.moneymanager.model.AccountModel
import kotlinx.coroutines.flow.Flow

class AccountRepository(
    private val accountDao: AccountDao
) {

    val allAccounts: Flow<List<AccountModel>> = accountDao.getAccountsFlow()

    @WorkerThread
    suspend fun insert(vararg accountModel: AccountModel) {
        accountDao.insertAccounts(*accountModel)
    }

    @WorkerThread
    suspend fun update(vararg accountModel: AccountModel) {
        accountDao.updateAccounts(*accountModel)
    }

    @WorkerThread
    suspend fun insertOrUpdate(vararg accountModel: AccountModel) {
        val currentIds = accountDao.getAccounts().map {
            it.id
        }
        accountModel.forEach {
            if (currentIds.contains(it.id)) {
                accountDao.updateAccounts(it)
            } else {
                accountDao.updateAccounts(it)
            }
        }
    }

    @WorkerThread
    suspend fun delete(accountModel: AccountModel) {
        accountDao.delete(accountModel)
    }
}