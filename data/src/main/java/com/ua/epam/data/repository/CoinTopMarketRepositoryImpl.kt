package com.ua.epam.data.repository

import com.ua.epam.domain.model.CurrencyEntity
import com.ua.epam.domain.repository.CoinTopMarketRepository
import io.reactivex.Observable

class CoinTopMarketRepositoryImpl(private val remoteCryptoDataSource: RemoteCryptoDataSource) : CoinTopMarketRepository {
    override fun getCryptoCurrencyMarketList(query: String): Observable<List<CurrencyEntity>> {
       return remoteCryptoDataSource.getCurrencyList(query)
    }
}