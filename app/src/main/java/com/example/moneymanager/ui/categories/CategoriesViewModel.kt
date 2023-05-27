package com.example.moneymanager.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.moneymanager.db.repository.CategoryRepository
import com.example.moneymanager.model.CategoryModel

class CategoriesViewModel(private val repository: CategoryRepository) :
    ViewModel() {

    val categoriesListLiveData: LiveData<List<CategoryModel>> =
        repository.allCategories.asLiveData()

    class CategoryViewModelFactory(private val repository: CategoryRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CategoriesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}