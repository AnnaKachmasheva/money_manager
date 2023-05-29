package com.example.moneymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.dao.CategoryDao
import com.example.moneymanager.model.CategoryModel

class CategoryRepository(private val categoryDao: CategoryDao) {

    val readAllData: LiveData<List<CategoryModel>> = categoryDao.readAllData()

    suspend fun addCategory(categoryModel: CategoryModel) {
        categoryDao.insert(categoryModel)
    }

    suspend fun updateCategory(categoryModel: CategoryModel) {
        categoryDao.update(categoryModel)
    }

    suspend fun deleteCategory(categoryModel: CategoryModel) {
        categoryDao.delete(categoryModel)
    }

}