package com.example.moneymanager.model

import androidx.room.Embedded
import androidx.room.Entity
import com.example.moneymanager.model.enums.TransactionType
import java.time.LocalDate


@Entity(tableName = "expenses_incomes")
class ExpensesIncomeModel(
    id: Long,
    type: TransactionType,
    amount: Double,
    date: LocalDate,
    note: String,
    @Embedded val account: AccountModel?,
    @Embedded val category: CategoryModel?
//    @Relation(entityColumn = "photo_address")
//    val photo: List<PhotoModel>? = null,
) : TransactionModel(id, type, amount, date, note) {}