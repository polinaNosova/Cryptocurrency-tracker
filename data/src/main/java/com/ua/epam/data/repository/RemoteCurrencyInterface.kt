package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.FullCurrency

interface RemoteCurrencyInterface {
    suspend fun getFinCurrencyList(): Result<List<FullCurrency>>
    suspend fun insert(currencyEntity: Currency)
    suspend fun update(list: List<Currency>)
    suspend fun getAllCoins(): List<Currency>
}