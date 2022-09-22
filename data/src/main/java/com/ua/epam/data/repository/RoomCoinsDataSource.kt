package com.ua.epam.data.repository

import com.ua.epam.data.mapper.LocalCurrencyMapper
import com.ua.epam.data.storage.local.dao.CoinDao
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency

class RoomCoinsDataSource(private val dao: CoinDao) : LocalCoinsDataSource {

    override suspend fun addCoin(currency: Currency) {
        dao.addCoin(LocalCurrencyMapper.mapCurrencyToEntity(currency))
    }

    override suspend fun deleteLocalCoin(currency: Currency) {
        dao.deleteCoin(LocalCurrencyMapper.mapCurrencyToEntity(currency))
    }

    override suspend fun getAllLocalCoins(): List<Currency> {
        return LocalCurrencyMapper.mapEntitiesToCurrencies(dao.getAllCoins())
    }

    override suspend fun getCoinById(id: String): Currency {
        return LocalCurrencyMapper.mapEntityToCurrency(dao.getCoinById(id))
    }
}