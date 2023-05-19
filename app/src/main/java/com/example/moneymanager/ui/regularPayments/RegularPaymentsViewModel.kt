package com.example.moneymanager.ui.regularPayments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegularPaymentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is regular payments Fragment"
    }
    val text: LiveData<String> = _text
}