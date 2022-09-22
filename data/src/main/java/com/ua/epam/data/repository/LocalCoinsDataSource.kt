package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency

interface LocalCoinsDataSource {
    suspend fun addCoin(currency: Currency)
    suspend fun deleteLocalCoin(currency: Currency)
    suspend fun getAllLocalCoins(): List<Currency>
    suspend fun getCoinById(id:String):Currency
}