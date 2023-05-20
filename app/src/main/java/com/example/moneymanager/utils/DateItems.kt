package com.example.moneymanager.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.moneymanager.model.DateModel
import java.time.LocalDate

object DateItems {

    @RequiresApi(Build.VERSION_CODES.O)
    val DateItems = arrayListOf(
        DateModel(date = LocalDate.now(), name = "Today"),
        DateModel(date = LocalDate.now().plusDays(1), name = "Yesterday")
    )

}