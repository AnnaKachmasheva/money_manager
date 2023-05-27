package com.example.moneymanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moneymanager.model.AccountModel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class AccountModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "amount") var amount: Float = 0.0f,
    @ColumnInfo(name = "include_in_total_balance") var isIncludeInTotalBalance: Boolean = true
) {
    companion object {
        const val TABLE_NAME = "accounts"
    }
}