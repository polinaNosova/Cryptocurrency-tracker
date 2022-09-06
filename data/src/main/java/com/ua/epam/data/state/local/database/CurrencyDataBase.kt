package com.ua.epam.data.state.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ua.epam.data.state.local.dao.CurrencyDao
import com.ua.epam.data.state.local.model.CurrencyDbEntity

@Database(
    entities = [CurrencyDbEntity::class],
    version = 1
)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}

private lateinit var INSTANCE: CurrencyDataBase

fun getDatabase(context: Context): CurrencyDataBase {

    synchronized(CurrencyDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CurrencyDataBase::class.java,
                "database"
            ).build()
        }
    }

    return INSTANCE
}