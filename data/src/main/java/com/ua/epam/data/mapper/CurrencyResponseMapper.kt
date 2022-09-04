package com.ua.epam.data.mapper

import com.ua.epam.data.remote.dto.MarketModelResponse
import com.ua.epam.domain.model.CurrencyEntity

object CurrencyResponseMapper {
    fun toCurrencyEntity(list: List<MarketModelResponse>): List<CurrencyEntity> =
        list.map {
            CurrencyEntity(
                it.id,
                it.symbol,
                it.image,
                it.name,
                it.currentPrice,
                it.lowTwentyFourHour,
                it.highTwentyFourHour,
                it.priceChangeResult
            )
        }
    }
