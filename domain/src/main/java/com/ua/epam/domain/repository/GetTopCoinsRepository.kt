package com.ua.epam.domain.repository

import com.ua.epam.domain.model.TopCoinEntity
import io.reactivex.Observable

interface GetTopCoinsRepository {
    fun getCryptoCurrencyMarketList(): Observable<List<TopCoinEntity>>
}