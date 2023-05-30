package com.example.moneymanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moneymanager.data.dao.AccountDao
import com.example.moneymanager.data.dao.CategoryDao
import com.example.moneymanager.data.dao.RegularPaymentDao
import com.example.moneymanager.model.AccountModel
import com.example.moneymanager.model.CategoryModel
import com.example.moneymanager.model.RegularPaymentModel
import com.example.moneymanager.model.convertets.DateConverter

@Database(
    entities = [
        AccountModel::class,
        CategoryModel::class,
        RegularPaymentModel::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun regularPaymentDao() : RegularPaymentDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "money_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}