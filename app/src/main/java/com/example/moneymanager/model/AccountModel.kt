package com.example.moneymanager.model

data class AccountModel(
    var name: String = "",
    var amount: Float = 0.0f,
    var isIncludeInTotalBalance: Boolean = true
) {
}