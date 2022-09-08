package com.ua.epam.data.state.remote.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ua.epam.data.state.remote.local.LocalCoinEntity

@Database(entities = [LocalCoinEntity::class], version = 1)
abstract class CoinsDb : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CoinsDb? = null

        fun getDatabase(context: Context): CoinsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinsDb::class.java,
                    "coins"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}