package com.ua.epam.domain.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.FullCurrency

interface CurrencyDataRepository {
    suspend fun getCryptoCurrencyMarketList(): Result<List<FullCurrency>>

    suspend fun insert(currency:Currency)

    suspend fun update(list: List<Currency>)

    suspend fun getAllCurrency(): List<Currency>
}
