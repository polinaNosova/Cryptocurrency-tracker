package com.ua.epam.data.repository

import com.ua.epam.domain.model.CurrencyEntity
import io.reactivex.Observable

interface RemoteCryptoDataSource {
     fun getCurrencyList(query:String):Observable<List<CurrencyEntity>>
}