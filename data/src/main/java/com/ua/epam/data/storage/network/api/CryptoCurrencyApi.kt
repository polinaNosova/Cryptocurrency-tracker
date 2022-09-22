package com.ua.epam.data.storage.network.api

import com.ua.epam.data.storage.network.models.CurrencyDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface CryptoCurrencyApi {
    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=true")
    suspend fun getSimpleRequest():Response<List<CurrencyDataResponse>>
}