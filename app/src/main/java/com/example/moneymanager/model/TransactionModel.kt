package com.example.moneymanager.model

import com.example.moneymanager.model.enums.TransactionType
import java.time.LocalDate

data class TransactionModel(
    val type: TransactionType,
    val amount: Float,
    val accountModel: AccountModel,
    val categoryModel: CategoryModel,
    val date: LocalDate,
    val note: String
) {}
