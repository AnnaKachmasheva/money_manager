package com.example.moneymanager.model

import androidx.room.Embedded
import androidx.room.Entity
import com.example.moneymanager.model.enums.TransactionType
import java.time.LocalDate


@Entity(tableName = "transfers")
class TransferModel(
    id: Long,
    type: TransactionType,
    amount: Double,
    date: LocalDate,
    note: String,
    @Embedded(prefix = "from_") val accountFrom: AccountModel?,
    @Embedded(prefix = "to_") val accountTo: AccountModel?,
) : TransactionModel(id, type, amount, date, note) {}
