package com.example.moneymanager.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneymanager.model.CurrencyModel
import com.example.moneymanager.utils.CurrencyUtils

class CurrenciesViewModel : ViewModel() {

    private val currencyListMutableLiveData: MutableLiveData<List<CurrencyModel>> by lazy {
        MutableLiveData<List<CurrencyModel>>().also {
            it.value = CurrencyUtils.CurrenciesList.toList()
        }
    }

    val currencyListLiveData: LiveData<List<CurrencyModel>> = currencyListMutableLiveData

    fun deleteCurrency(currencies: CurrencyModel) {
        CurrencyUtils.CurrenciesList.remove(currencies)
        currencyListMutableLiveData.value = CurrencyUtils.CurrenciesList.toList()
    }

}