package com.example.moneymanager.ui.regularPayments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.data.AppDatabase
import com.example.moneymanager.data.repository.AccountRepository
import com.example.moneymanager.data.repository.CategoryRepository
import com.example.moneymanager.data.repository.RegularPaymentRepository
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.model.RegularPaymentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegularPaymentsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<RegularPaymentModel>>
    val readAllAccounts: LiveData<List<AccountModel>>
    val readAllCategories: LiveData<List<CategoryModel>>

    private val repository: RegularPaymentRepository
    private val repositoryAccount: AccountRepository
    private val repositoryCategory: CategoryRepository

    init {
        val regularPaymentDao = AppDatabase.getDatabase(application).regularPaymentDao()
        val accountDao = AppDatabase.getDatabase(application).accountDao()
        val categoryDao = AppDatabase.getDatabase(application).categoryDao()

        repository = RegularPaymentRepository(regularPaymentDao)
        repositoryAccount = AccountRepository(accountDao)
        repositoryCategory = CategoryRepository(categoryDao)

        readAllData = repository.readAllData
        readAllAccounts = repositoryAccount.readAllData
        readAllCategories = repositoryCategory.readAllData
    }

    fun addRegularPayment(regularPaymentModel: RegularPaymentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRegularPayment(regularPaymentModel)
        }
    }

    fun updateRegularPayment(regularPaymentModel: RegularPaymentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRegularPayment(regularPaymentModel)
        }
    }

    fun deleteRegularPayment(regularPaymentModel: RegularPaymentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRegularPayment(regularPaymentModel)
        }
    }
}