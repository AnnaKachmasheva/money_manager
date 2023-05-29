package com.example.moneymanager.ui.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanager.data.AppDatabase
import com.example.moneymanager.data.repository.CategoryRepository
import com.example.moneymanager.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application) :
    AndroidViewModel(application) {

    val readAllData: LiveData<List<CategoryModel>>
    private val repository: CategoryRepository

    init {
        val categoryDao = AppDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        readAllData = repository.readAllData
    }

    fun addCategory(categoryModel: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(categoryModel)
        }
    }

    fun updateCategory(categoryModel: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCategory(categoryModel)
        }
    }

    fun deleteCategory(categoryModel: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(categoryModel)
        }
    }

}