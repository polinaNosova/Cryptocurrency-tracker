package com.ua.epam.data.repository

import com.ua.epam.domain.model.CurrencyEntity
import io.reactivex.Observable
import retrofit2.http.Query

interface GetRemoteCurrencyInterface {
    fun getFinCurrencyList(query: String): Observable<List<CurrencyEntity>>
    suspend fun insert(currencyEntity: CurrencyEntity)
    suspend fun update(list: List<CurrencyEntity>)
    suspend fun getAllCoins(): List<CurrencyEntity>
}