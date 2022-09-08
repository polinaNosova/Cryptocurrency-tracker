package com.ua.epam.data.mapper

import com.ua.epam.data.state.remote.local.db.entity.CoinsEntity
import com.ua.epam.domain.model.Currency

object LocalCurrencyMapper {
    fun mapCurrenciesToLocalCoinEntities(list: List<Currency>): List<CoinsEntity> =
        list.map {
           CoinsEntity(
                it.id, it.symbol, it.image, it.name, it.currentPrice
            )
        }

    fun mapCurrencyToEntity(currency: Currency): CoinsEntity =
        CoinsEntity(
            currency.id,
            currency.symbol,
            currency.image,
            currency.name,
            currency.currentPrice
        )

    fun mapEntityToCurrency(list: List<CoinsEntity>): List<Currency> =
        list.map {
            Currency(
                it.id.toString(), it.symbol, it.image, it.name, it.currentPrice
            )
        }
}
