package com.ua.epam.data.repository

import android.annotation.SuppressLint
import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.network.CryptoCurrencyService
import com.ua.epam.domain.model.CurrencyEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetRemoteCurrencyInterfaceImpl(private val service: CryptoCurrencyService) :
    GetRemoteCurrencyInterface {
    @SuppressLint("CheckResult")
    override fun getFinCurrencyList(query: String): Observable<List<CurrencyEntity>> {
        return service.getSimpleRequest(query)
            .subscribeOn(Schedulers.io())
            .map { CurrencyResponseMapper.toCurrencyEntity(it) }
    }
}