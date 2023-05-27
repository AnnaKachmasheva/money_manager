package com.example.moneymanager.db.dao

import androidx.room.*
import com.example.moneymanager.model.AccountModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts ORDER BY id ASC")
    fun getAccountsFlow(): Flow<List<AccountModel>>

    @Query("SELECT * FROM accounts ORDER BY id ASC")
    suspend fun getAccounts(): List<AccountModel>

    @Query("SELECT * FROM accounts WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): AccountModel?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccounts(vararg accountModel: AccountModel)

    @Update
    suspend fun updateAccounts(vararg accountModel: AccountModel)

    @Query("DELETE FROM accounts")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(item: AccountModel)

}