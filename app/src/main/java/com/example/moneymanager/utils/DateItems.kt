package com.example.moneymanager.utils

import com.example.moneymanager.model.DateModel
import java.time.LocalDate

object DateItems {

    val DateItems = arrayListOf(
        DateModel(date = LocalDate.now(), name = "Today"),
        DateModel(date = LocalDate.now().plusDays(1), name = "Yesterday")
    )

}