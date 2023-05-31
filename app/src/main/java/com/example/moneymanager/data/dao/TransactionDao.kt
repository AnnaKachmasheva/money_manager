package com.example.moneymanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.model.TransferModel

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpensesIncome(expensesIncomeModel: ExpensesIncomeModel)

    @Query("SELECT * FROM expenses_incomes WHERE type IS 'EXPENSES'")
    fun readAllDataExpenses(): LiveData<List<ExpensesIncomeModel>>

    @Query("SELECT * FROM expenses_incomes WHERE type IS 'EXPENSES' AND category_id = :id")
    fun getExpensesByCategoryId(id: Int?): LiveData<List<ExpensesIncomeModel>>

    @Query("SELECT * FROM expenses_incomes WHERE type IS 'INCOME'")
    fun readAllDataIncome(): LiveData<List<ExpensesIncomeModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransfer(transferModel: TransferModel)

    @Query("SELECT * FROM transfers ORDER BY id ASC")
    fun readAllDataTransfer(): LiveData<List<TransferModel>>

    @Query("SELECT sum(amount) FROM transfers")
    fun getTotalTransferAmount(): LiveData<Double>

    @Delete
    fun deleteTransfer(vararg transferModel: TransferModel)

    @Update
    fun updateTransfer(transferModel: TransferModel)

    @Query("SELECT sum(amount) FROM expenses_incomes WHERE type IS 'EXPENSES'")
    fun getTotalExpencesAmount(): LiveData<Double>

    @Delete
    fun deleteTransaction(vararg model: ExpensesIncomeModel)

    @Update
    fun updateTransaction(model: ExpensesIncomeModel)

    @Query("SELECT sum(amount) FROM expenses_incomes WHERE type IS 'INCOME'")
    fun getTotalIncomeAmount(): LiveData<Double>
}