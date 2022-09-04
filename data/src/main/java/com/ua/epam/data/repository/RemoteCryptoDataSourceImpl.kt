package com.ua.epam.data.repository

import android.annotation.SuppressLint
import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.remote.api.CurrencyService
import com.ua.epam.domain.model.CurrencyEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RemoteCryptoDataSourceImpl(private val cryptoCurrencyService: CurrencyService) :
    RemoteCryptoDataSource {
    @SuppressLint("CheckResult")
    override fun getCurrencyList(query: String): Observable<List<CurrencyEntity>> {
        return cryptoCurrencyService.getSimpleRequest(query)
            .subscribeOn(Schedulers.newThread())
            .map { CurrencyResponseMapper.CurrencyResponseToCurrencyEntity(it) }
    }
}
