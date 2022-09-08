package com.ua.epam.data.state.remote.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CoinsEntity(
    @PrimaryKey()
    val id: String,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double
)

