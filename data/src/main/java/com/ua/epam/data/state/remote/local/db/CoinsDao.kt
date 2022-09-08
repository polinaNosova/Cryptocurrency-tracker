package com.ua.epam.data.state.remote.local.db

import androidx.room.*
import com.ua.epam.data.state.remote.local.db.entity.CoinsEntity

@Dao
interface CoinsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coinEntity:CoinsEntity)

    @Update
    suspend fun update(list: List<CoinsEntity>)

    @Query("SELECT * FROM crypto ORDER BY id ASC")
    fun getAllCurrency(): List<CoinsEntity>

}