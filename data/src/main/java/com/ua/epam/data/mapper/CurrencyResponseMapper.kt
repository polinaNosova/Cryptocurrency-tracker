package com.ua.epam.data.mapper

import com.ua.epam.data.state.remote.model.CurrencyDataResponse
import com.ua.epam.data.state.remote.model.TopCoinResponse
import com.ua.epam.domain.model.FullCurrency
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.TopCoinEntity

object CurrencyResponseMapper {
    fun toCurrencyEntity(list: List<CurrencyDataResponse>): List<FullCurrency> =
        list.map {
            FullCurrency(
                Currency(
                    it.id,
                    it.symbol,
                    it.image,
                    it.name,
                    it.current_price
                ),
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

    fun toCoinTopEntity(list: List<TopCoinResponse>): List<TopCoinEntity> =
        list.map {
            TopCoinEntity(
                it.item.toItemEntity()
            )
        }
}
