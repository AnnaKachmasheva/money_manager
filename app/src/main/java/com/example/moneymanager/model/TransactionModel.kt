package com.example.moneymanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moneymanager.model.enums.TransactionType
import java.time.LocalDate


@Entity(tableName="transactions")
abstract class TransactionModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id") var id: Long = 0,
    @ColumnInfo(name = "transaction_type") val type: TransactionType?,
    @ColumnInfo(name = "transaction_amount") val amount: Double = 0.0,
    @ColumnInfo(name = "transaction_date") val date: LocalDate,
    @ColumnInfo(name = "transaction_note") val note: String?
) {}
