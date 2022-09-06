package com.ua.epam.data.state.local.dao

import androidx.room.*
import com.ua.epam.data.state.local.model.CurrencyDbEntity

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rateChangeEntity: CurrencyDbEntity)

    @Update
    suspend fun update(list: List<CurrencyDbEntity>)

    @Query("SELECT * FROM currency ORDER BY id ASC")
    fun getCoinsList(): List<CurrencyDbEntity>
}