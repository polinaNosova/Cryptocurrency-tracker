package com.ua.epam.data.repository

import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.storage.network.api.CryptoCurrencyApi
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency

class RetrofitCoinsDataSource(private val api: CryptoCurrencyApi) : NetworkCoinsDataSource {
    override suspend fun getCoinsList():Result<List<Currency>> {
        return try {
            val response = api.getSimpleRequest()
            if (response.isSuccessful) {
                Result.Success(CurrencyResponseMapper.toCurrency(response.body()!!))
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}