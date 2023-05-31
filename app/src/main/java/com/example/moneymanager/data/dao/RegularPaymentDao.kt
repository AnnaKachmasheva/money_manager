package com.example.moneymanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneymanager.model.RegularPaymentModel

@Dao
interface RegularPaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(regularPaymentModel: RegularPaymentModel)

    @Query("SELECT * FROM regular_payments ORDER BY regular_payment_id ASC")
    fun readAllData(): LiveData<List<RegularPaymentModel>>

    @Update
    fun update(regularPaymentModel: RegularPaymentModel)

    @Delete
    fun delete(vararg regularPaymentModel: RegularPaymentModel)

}