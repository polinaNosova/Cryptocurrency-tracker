package com.ua.epam.domain.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency

interface CoinsDataRepository {
    suspend fun addCoin(currency: Currency)
    suspend fun deleteLocalCoin(currency: Currency)
    suspend fun getAllLocalCoins(): List<Currency>
    suspend fun getCoinsList(): Result<List<Currency>>
    suspend fun getCoinById(id:String):Currency
}