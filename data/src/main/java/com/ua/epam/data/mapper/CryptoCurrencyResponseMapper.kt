package com.ua.epam.data.mapper

import com.ua.epam.data.remote.dto.CryptoCurrency
import com.ua.epam.domain.model.CryptoCurrencyEntity

object CryptoCurrencyResponseMapper {
    fun toCryptoCurrencyEntity(list: List<CryptoCurrency>): List<CryptoCurrencyEntity> =
        list.map {
            CryptoCurrencyEntity(
                it.quotes.map { it.toQuetyEntity() },
                it.symbol,
                it.slug
            )
        }
    }