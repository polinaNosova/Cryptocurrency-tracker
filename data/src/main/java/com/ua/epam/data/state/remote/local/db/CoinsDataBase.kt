package com.ua.epam.data.state.remote.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ua.epam.data.state.remote.local.db.entity.CoinsEntity

@Database(
    entities = [CoinsEntity::class],
    version = 1
)
abstract class CoinsDataBase : RoomDatabase() {
    abstract fun getCoinsDao(): CoinsDao
    companion object {
        @Volatile
        private var INSTANCE: CoinsDataBase? = null

        fun getDatabase(context: Context): CoinsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinsDataBase::class.java,
                    "crypto"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
