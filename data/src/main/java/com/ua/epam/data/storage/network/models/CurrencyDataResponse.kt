package com.ua.epam.data.storage.network.models

import com.google.gson.annotations.SerializedName

data class CurrencyDataResponse(
    val name: String,
    var id: String,
    val symbol: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Long,
    @SerializedName("high_24h")
    val highTwentyFourHour: Double,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("low_24h")
    val lowTwentyFourH: Double,
    @SerializedName("market_cap")
    val marketCap: Long,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("price_change_24h")
    val priceChangeTwentyFourH: Double,
    @SerializedName("sparkline_in_7d")
    val sparklineInSevenDays: SparkLineInSevenDayResponse,
)
