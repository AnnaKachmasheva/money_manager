package com.example.moneymanager.db.repository

import androidx.annotation.WorkerThread
import com.example.moneymanager.db.dao.CategoryDao
import com.example.moneymanager.model.CategoryModel
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    private val categoryDao: CategoryDao
) {

    val allCategories: Flow<List<CategoryModel>> = categoryDao.getCategoriesFlow()

    @WorkerThread
    suspend fun insert(vararg categoryModel: CategoryModel) {
        categoryDao.insertPlaygrounds(*categoryModel)
    }

    @WorkerThread
    suspend fun update(vararg categoryModel: CategoryModel) {
        categoryDao.updatePlaygrounds(*categoryModel)
    }

    @WorkerThread
    suspend fun insertOrUpdate(vararg categoryModel: CategoryModel) {
        val currentIds = categoryDao.getCategories().map {
            it.id
        }
        categoryModel.forEach {
            if (currentIds.contains(it.id)) {
                categoryDao.updatePlaygrounds(it)
            } else {
                categoryDao.insertPlaygrounds(it)
            }
        }
    }

    @WorkerThread
    suspend fun delete(categoryModel: CategoryModel) {
        categoryDao.delete(categoryModel)
    }
}