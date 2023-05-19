package com.example.moneymanager.model

import com.example.moneymanager.model.enums.FrequencyRegularPayment
import com.example.moneymanager.model.enums.TransactionType
import java.time.LocalDate

data class RegularPaymentModel(
    var name: String = "",
    var amount: Float,
    var type: TransactionType = TransactionType.EXPENSES,
    var account: AccountModel,
    var category: CategoryModel,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var frequency: FrequencyRegularPayment = FrequencyRegularPayment.MONTHLY,
    var isActive: Boolean
) {
}