package com.ua.epam.data.mapper

import com.ua.epam.data.model.CurrencyDataResponse
import com.ua.epam.domain.model.CurrencyEntity
import com.ua.epam.domain.model.SparklineIn7dEntity

object CurrencyResponseMapper {
    fun toCurrencyEntity(list: List<CurrencyDataResponse>): List<CurrencyEntity> =
        list.map {
            CurrencyEntity(
                it.id,
                it.symbol,
                it.image,
                it.name,
                it.current_price,
                it.low_24h,
                it.high_24h,
                it.priceChange24h,
                it.sparklineIn7d.toSparklineIn7dEntity(),
                it.market_cap_rank,
                it.circulating_supply,
                it.ath_date,
                it.market_cap
            )
        }
}
