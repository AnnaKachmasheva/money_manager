package com.example.moneymanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moneymanager.db.dao.AccountDao
import com.example.moneymanager.db.dao.CategoryDao
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.model.CategoryModel
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [CategoryModel::class, AccountModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "money_manager_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}