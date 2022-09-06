package com.ua.epam.domain.repository

import com.ua.epam.domain.model.CurrencyEntity
import io.reactivex.Observable

interface GetCurrencyDataRepository {
    fun getCryptoCurrencyMarketList(
        query: String
    ): Observable<List<CurrencyEntity>>
}
