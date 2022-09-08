package com.ua.epam.data.state.remote.local.db

import androidx.room.*
import com.ua.epam.data.state.remote.local.LocalCoinEntity

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rateChangeEntity: LocalCoinEntity)

    @Update
    suspend fun update(list: List<LocalCoinEntity>)

    @Query("SELECT * FROM coins ORDER BY id ASC")
    fun getAllCurrency(): List<LocalCoinEntity>
}
