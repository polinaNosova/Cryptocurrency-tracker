package com.ua.epam.data.repository

import com.ua.epam.domain.model.CurrencyEntity
import com.ua.epam.domain.repository.GetCurrencyDataRepository
import io.reactivex.Observable

class GetCurrencyDataRepositoryImpl(private val getRemoteCurrencyInterface: GetRemoteCurrencyInterface) :
    GetCurrencyDataRepository {
    override fun getCryptoCurrencyMarketList(query: String): Observable<List<CurrencyEntity>> {
        return getRemoteCurrencyInterface.getFinCurrencyList(query)
    }
}