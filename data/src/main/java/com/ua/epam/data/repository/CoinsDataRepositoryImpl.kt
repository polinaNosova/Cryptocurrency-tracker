package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.repository.CoinsDataRepository

class CoinsDataRepositoryImpl(
    private val networkCoinsDataSource: NetworkCoinsDataSource,
    private val localCoinsDataSource: LocalCoinsDataSource,
) : CoinsDataRepository {

    override suspend fun addCoin(currency: Currency) {
        localCoinsDataSource.addCoin(currency)
    }

    override suspend fun deleteLocalCoin(currency: Currency) {
        localCoinsDataSource.deleteLocalCoin(currency)
    }

    override suspend fun getAllLocalCoins(): List<Currency> =
        localCoinsDataSource.getAllLocalCoins()

    override suspend fun getCoinById(id: String): Currency {
        return localCoinsDataSource.getCoinById(id)
    }

    override suspend fun getCoinsList(): Result<List<Currency>> {
        return networkCoinsDataSource.getCoinsList()
    }
}