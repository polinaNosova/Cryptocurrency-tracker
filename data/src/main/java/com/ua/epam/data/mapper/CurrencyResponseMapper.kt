package com.ua.epam.data.mapper

import com.ua.epam.data.storage.network.models.CurrencyDataResponse
import com.ua.epam.domain.model.Currency

object CurrencyResponseMapper {
    fun toCurrency(list: List<CurrencyDataResponse>): List<Currency> =
        list.map {
            Currency(
                it.id,
                it.symbol,
                it.image,
                it.name,
                it.currentPrice,
                it.lowTwentyFourH,
                it.highTwentyFourHour,
                it.priceChangeTwentyFourH,
                it.sparklineInSevenDays.fromPrice(),
                it.marketCapRank,
                it.marketCap
            )
        }
    }
