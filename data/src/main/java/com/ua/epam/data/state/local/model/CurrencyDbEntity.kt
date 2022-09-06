package com.ua.epam.data.state.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ua.epam.domain.model.SparklineIn7dEntity

@Entity(tableName = "currency")
data class CurrencyDbEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double
)
