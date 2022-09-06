package com.ua.epam.data.repository

import com.ua.epam.domain.model.TopCoinEntity
import com.ua.epam.domain.repository.GetTopCoinsRepository
import io.reactivex.Observable

class GetTopCoinsListImpl(private val getRemoteTopCoinInterface: GetRemoteTopCoinInterface) :
    GetTopCoinsRepository {
    override fun getCryptoCurrencyMarketList(): Observable<List<TopCoinEntity>> {
        return getRemoteTopCoinInterface.getFindTopCoinsList()
    }
}