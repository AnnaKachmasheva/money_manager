package com.example.moneymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.dao.RegularPaymentDao
import com.example.moneymanager.model.RegularPaymentModel

class RegularPaymentRepository(private val regularPaymentDao: RegularPaymentDao) {

    val readAllData: LiveData<List<RegularPaymentModel>> = regularPaymentDao.readAllData()


    suspend fun addRegularPayment(regularPaymentModel: RegularPaymentModel) {
        regularPaymentDao.insert(regularPaymentModel)
    }

    suspend fun updateRegularPayment(regularPaymentModel: RegularPaymentModel) {
        regularPaymentDao.update(regularPaymentModel)
    }

    suspend fun deleteRegularPayment(regularPaymentModel: RegularPaymentModel) {
        regularPaymentDao.delete(regularPaymentModel)

    }

}