package com.ua.epam.data.storage.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ua.epam.data.storage.local.converter.Converters
import com.ua.epam.data.storage.local.dao.CoinDao
import com.ua.epam.data.storage.local.entities.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CoinDB : RoomDatabase() {
    abstract fun getDao(): CoinDao

    companion object {
        @Volatile
        private var INSTANCE: CoinDB? = null
        fun getInstance(context: Context): CoinDB {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CoinDB::class.java,
                    "coins.db")
                    .build()
            }
        }
    }
}