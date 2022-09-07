package com.ua.epam.data.repository

import android.annotation.SuppressLint
import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.state.network.CryptoCurrencyService
import com.ua.epam.domain.model.CurrencyEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetRemoteCurrencyInterfaceImpl(
    private val service: CryptoCurrencyService
) :
    GetRemoteCurrencyInterface {
    @SuppressLint("CheckResult")
    override fun getFinCurrencyList(query: String): Observable<List<CurrencyEntity>> {
        return service.getSimpleRequest(query)
            .subscribeOn(Schedulers.io())
            .map { CurrencyResponseMapper.toCurrencyEntity(it) }
    }

    override suspend fun insert(currencyEntity: CurrencyEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun update(list: List<CurrencyEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCoins(): List<CurrencyEntity> {
        TODO("Not yet implemented")
    }

}