package com.ua.epam.data.mapper

import com.ua.epam.data.state.remote.local.LocalCoinEntity
import com.ua.epam.domain.model.Currency

object LocalCurrencyMapper {
    fun mapCurrenciesToLocalCoinEntities(list: List<Currency>): List<LocalCoinEntity> =
        list.map {
            LocalCoinEntity(
                it.id.toInt(), it.symbol, it.image, it.name, it.currentPrice
            )
        }

    fun mapCurrencyToEntity(currency: Currency): LocalCoinEntity =
        LocalCoinEntity(
            currency.id.toInt(),
            currency.symbol,
            currency.image,
            currency.name,
            currency.currentPrice
        )

    fun mapEntityToCurrency(list: List<LocalCoinEntity>): List<Currency> =
        list.map {
            Currency(
                it.id.toString(), it.symbol, it.image, it.name, it.currentPrice
            )
        }
}
