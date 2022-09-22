package com.ua.epam.domain.model

data class Currency(
    val id: String,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double,
    val lowTwentyFourHour: Double,
    val highTwentyFourHour: Double,
    val priceChangeResult: Double,
    val sparkline_in_7d: SparklineInSevenDays,
    val market_cap_rank: Int,
    val market_cap: Long,
)
