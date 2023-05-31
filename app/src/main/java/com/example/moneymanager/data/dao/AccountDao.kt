package com.example.moneymanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneymanager.model.AccountModel

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(accountModel: AccountModel)

    @Query("SELECT * FROM accounts ORDER BY account_id ASC")
    fun readAllData(): LiveData<List<AccountModel>>

    @Query("SELECT sum(account_amount) FROM accounts WHERE isIncludeInTotalBalance IS 1")
    fun getTotalAmount(): LiveData<Double>

    @Update
    fun update(accountModel: AccountModel)

    @Delete
    fun delete(vararg accountModel: AccountModel)

}