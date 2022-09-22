package com.ua.epam.data.storage.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ua.epam.data.storage.local.converter.Converters
import com.ua.epam.data.storage.local.entities.CoinEntity.Companion.TABLE_NAME
import com.ua.epam.domain.model.Currency

@Entity(tableName = TABLE_NAME)
data class CoinEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    val symbol: String,
    val image: String,
    val name: String,
    @ColumnInfo(name = "current_price")
    val currentPrice: Double,
    @ColumnInfo(name = "high_24h")
    val highTwentyFourHour: Double,
    @ColumnInfo(name = "low_24h")
    val lowTwentyFourH: Double,
    @ColumnInfo(name = "market_cap")
    val marketCap: Long,
    @ColumnInfo(name = "market_cap_rank")
    val marketCapRank: Int,
    @ColumnInfo(name = "price_change_24h")
    val priceChangeTwentyFourH: Double,
    @ColumnInfo(name = "sparkline_in_7d")
    val sparklineInSevenD: ArrayList<Float>
) {
    companion object {
        const val TABLE_NAME = "coins"
    }
}