package com.example.moneymanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneymanager.model.CategoryModel

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoryModel: CategoryModel)

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun readAllData(): LiveData<List<CategoryModel>>

    @Update
    fun update(categoryModel: CategoryModel)

    @Delete
    fun delete(vararg categoryModel: CategoryModel)
}