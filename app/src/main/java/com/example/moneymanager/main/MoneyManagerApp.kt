package com.example.moneymanager.main

import android.app.Application
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MoneyManagerApp : Application() {

    companion object {
        var patternNumber = "###,###,###,###,###.#"
        val numberFormat = DecimalFormat(patternNumber, DecimalFormatSymbols(Locale.ENGLISH))
        var datePattern = "dd MMMM yyyy"
    }

    override fun onCreate() {
        super.onCreate()
    }

}