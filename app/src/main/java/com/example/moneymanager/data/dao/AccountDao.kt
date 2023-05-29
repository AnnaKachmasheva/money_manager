package com.example.moneymanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moneymanager.model.AccountModel

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(accountModel: AccountModel)

    @Query("SELECT * FROM accounts_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<AccountModel>>

//    @Query("SELECT * FROM accounts ORDER BY id ASC")
//    fun getAccountsFlow(): Flow<List<AccountModel>>
//
//    @Query("SELECT * FROM accounts WHERE id IN (:accountId)")
//    fun loadAllByIds(accountId: IntArray): List<AccountModel>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg accountModels: AccountModel)
//
//    @Delete
//    fun delete(vararg accountModel: AccountModel)
//
//    @Query("DELETE FROM accounts")
//    suspend fun deleteAll()
}