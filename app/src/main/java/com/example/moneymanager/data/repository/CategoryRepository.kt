package com.example.moneymanager.data.repository

import androidx.lifecycle.LiveData
import com.example.moneymanager.data.dao.CategoryDao
import com.example.moneymanager.model.CategoryModel

class CategoryRepository(private val categoryDao: CategoryDao) {

    val readAllData: LiveData<List<CategoryModel>> = categoryDao.readAllData()

    suspend fun addCategory(categoryModel: CategoryModel) {
        categoryDao.insert(categoryModel)
    }

    fun updateCategory(categoryModel: CategoryModel) {
        categoryDao.update(categoryModel)
    }

    fun deleteCategory(categoryModel: CategoryModel) {
        categoryDao.delete(categoryModel)
    }

}