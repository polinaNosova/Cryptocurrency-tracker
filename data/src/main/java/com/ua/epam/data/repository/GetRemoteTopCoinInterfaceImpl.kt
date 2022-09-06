package com.ua.epam.data.repository

import android.annotation.SuppressLint
import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.state.network.CryptoCurrencyService
import com.ua.epam.domain.model.TopCoinEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetRemoteTopCoinInterfaceImpl(private val service: CryptoCurrencyService) :
    GetRemoteTopCoinInterface {
    @SuppressLint("CheckResult")
    override fun getFindTopCoinsList(): Observable<List<TopCoinEntity>> {
        return service.getTopCoinsRequest()
            .subscribeOn(Schedulers.io())
            .map { CurrencyResponseMapper.toCoinTopEntity(it) }
    }
}