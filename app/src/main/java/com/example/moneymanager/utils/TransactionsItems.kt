package com.example.moneymanager.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.model.RegularPaymentModel
import com.example.moneymanager.model.TransactionModel
import com.example.moneymanager.model.enums.FrequencyRegularPayment
import com.example.moneymanager.model.enums.TransactionType
import java.time.LocalDate

object TransactionsItems {

    @RequiresApi(Build.VERSION_CODES.O)
    val TransactionsItems = arrayListOf(
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 123f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[0],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 342f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[1],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 121f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[2],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 6666f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[3],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 6666f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[3],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 6666f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[3],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 342f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[1],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 121f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[2],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 6666f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[3],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 6666f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[3],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        ),
        TransactionModel(
            type = TransactionType.EXPENSES,
            amount = 6666f,
//            accountModel = AccountModel("main"),
            categoryModel = CategoriesItems.CategoriesItems[3],
            date = LocalDate.of(2023, 11, 4),
            note = "-"
        )


    )

}