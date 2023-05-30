package com.example.moneymanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.model.ExpensesIncomeModel
import com.example.moneymanager.model.TransferModel

@Dao
interface TransactionDao {

    //income, expenses
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpensesIncome(expensesIncomeModel: ExpensesIncomeModel)

    @Query("SELECT * FROM expenses_incomes WHERE transaction_type IS 'EXPENSES' ORDER BY transaction_id ASC")
    fun readAllDataExpenses(): LiveData<List<ExpensesIncomeModel>>

    @Query("SELECT * FROM expenses_incomes WHERE transaction_type IS 'INCOME' ORDER BY transaction_id ASC")
    fun readAllDataIncome(): LiveData<List<ExpensesIncomeModel>>


    //transfers
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
//
//    @Query("SELECT * FROM accounts ORDER BY account_id ASC")
//    suspend fun getAllAccounts(): List<AccountModel>
//
//    @Query("SELECT sum(account_amount) FROM accounts WHERE isIncludeInTotalBalance IS 1")
//    fun getTotalAmount(): LiveData<Double>
//
//    @Update
//    fun update(accountModel: AccountModel)
//
//    @Delete
//    fun delete(vararg accountModel: AccountModel)
//
//    @Query("DELETE FROM accounts")
//    suspend fun deleteAll()
}