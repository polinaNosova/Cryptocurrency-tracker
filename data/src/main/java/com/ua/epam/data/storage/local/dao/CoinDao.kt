package com.ua.epam.data.storage.local.dao

import androidx.room.*
import com.ua.epam.data.storage.local.entities.CoinEntity
import com.ua.epam.domain.common.Result

@Dao
interface CoinDao {
    @Insert(entity = CoinEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoin(currencyEntity: CoinEntity)

    @Delete(entity = CoinEntity::class)
    suspend fun deleteCoin(currencyEntity: CoinEntity)

    @Query("SELECT * FROM ${CoinEntity.TABLE_NAME}")
    fun getAllCoins(): List<CoinEntity>

    @Query("SELECT * FROM ${CoinEntity.TABLE_NAME} WHERE id =:id ")
    fun getCoinById(id:String):CoinEntity
}