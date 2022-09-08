package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.FullCurrency
import com.ua.epam.domain.repository.CurrencyDataRepository

class CurrencyDataRepositoryImpl(private val remoteCurrencyInterface: RemoteCurrencyInterface) :
    CurrencyDataRepository {
    override suspend fun getCryptoCurrencyMarketList(): Result<List<FullCurrency>> {
        return remoteCurrencyInterface.getFinCurrencyList()
    }

    override suspend fun insert(currency: Currency) {
       remoteCurrencyInterface.insert(currency)
    }

    override suspend fun update(currencies: List<Currency>) {
        remoteCurrencyInterface.update(currencies)
    }

    override suspend fun getAllCurrency(): List<Currency> {
     return remoteCurrencyInterface.getAllCoins()
    }
}