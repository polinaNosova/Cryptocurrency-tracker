package com.ua.epam.domain.model

data class FullCurrency(
    val currencyEntity: Currency,
    val lowTwentyFourHour: Double,
    val highTwentyFourHour: Double,
    val priceChangeResult: Double,
    val sparkline_in_7d: SparklineIn7dEntity,
    val market_cap_rank: Int,
    val circulating_supply: Double,
    val ath_date: String,
    val market_cap: Long
)

data class Currency(
    val id: String,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double
)