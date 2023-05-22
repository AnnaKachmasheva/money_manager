package com.example.moneymanager.utils

import android.app.Application
import com.example.moneymanager.model.CurrencyModel
import com.example.sp_v2.R
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import java.io.InputStream


object CurrencyUtils {
    private var mapper: ObjectMapper = jacksonObjectMapper()

    val CurrenciesList: ArrayList<CurrencyModel> = ArrayList()

    fun loadInitCurrencies(app: Application) {
        var tmpCurrencyList = mutableListOf<CurrencyModel>()
        val inputStream: InputStream = app.resources.openRawResource(R.raw.currencies)
        val json = inputStream.bufferedReader().use { it.readText() }

        try {

            val currencies: List<CurrencyModel> =
                Gson().fromJson(json, Array<CurrencyModel>::class.java).asList()

            tmpCurrencyList = currencies as MutableList<CurrencyModel>

            CurrenciesList.clear()
            CurrenciesList.addAll(tmpCurrencyList)
        } catch (_: Exception) {

        }
    }


}