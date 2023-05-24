package com.example.moneymanager.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moneymanager.ui.regularPayments.RegularPaymentFragment
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(RegularPaymentFragment::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun regularPaymentDao(): PlaygroundDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PlaygroundTruthDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaygroundTruthDatabase::class.java,
                    "playground_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}