package com.ua.epam.data.repository

import com.ua.epam.domain.model.CurrencyEntity
import com.ua.epam.domain.repository.CoinHomeFragmentRepository
import io.reactivex.Observable

class CoinHomeFragmentRepositoryImpl(private val remoteCryptoDataSource: RemoteCryptoDataSource) :
    CoinHomeFragmentRepository {
    override fun getCryptoCurrencyTopMarketList(query: String): Observable<List<CurrencyEntity>> {
        return remoteCryptoDataSource.getCurrencyList(query)
    }
}