package com.example.moneymanager.db.dao

import androidx.room.*
import com.example.moneymanager.model.CategoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun getCategoriesFlow(): Flow<List<CategoryModel>>

    @Query("SELECT * FROM categories ORDER BY id ASC")
    suspend fun getCategories(): List<CategoryModel>

    @Query("SELECT * FROM categories WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): CategoryModel?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaygrounds(vararg categoryModel: CategoryModel)

    @Update
    suspend fun updatePlaygrounds(vararg categoryModel: CategoryModel)

    @Query("DELETE FROM categories")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(item: CategoryModel)
}