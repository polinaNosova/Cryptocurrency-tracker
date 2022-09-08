package com.ua.epam.data.state.remote.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class LocalCoinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double
)
