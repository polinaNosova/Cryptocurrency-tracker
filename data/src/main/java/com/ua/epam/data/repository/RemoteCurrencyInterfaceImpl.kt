package com.ua.epam.data.repository

import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.mapper.LocalCurrencyMapper
import com.ua.epam.data.state.remote.api.CryptoCurrencyService
import com.ua.epam.data.state.remote.local.db.CoinsDao
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.FullCurrency
import com.ua.epam.domain.model.Currency

class RemoteCurrencyInterfaceImpl(
    private val service: CryptoCurrencyService,
    private val dao: CoinsDao
) :
    RemoteCurrencyInterface {
    override suspend fun getFinCurrencyList():Result<List<FullCurrency>> {
        return try {
            val response = service.getSimpleRequest()
            if (response.isSuccessful) {
                Result.Success(CurrencyResponseMapper.toCurrencyEntity(response.body()!!))
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    override suspend fun insert(currencyEntity: Currency) {
        dao.insert(LocalCurrencyMapper.mapCurrencyToEntity(currencyEntity))
    }

    override suspend fun update(list: List<Currency>) {
        dao.update(LocalCurrencyMapper.mapCurrenciesToLocalCoinEntities(list))
    }

    override suspend fun getAllCoins(): List<Currency> {
        return LocalCurrencyMapper.mapEntityToCurrency(dao.getAllCurrency())
    }
}