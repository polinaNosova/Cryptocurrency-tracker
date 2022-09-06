package com.ua.epam.data.state.network

import com.ua.epam.data.state.network.model.CurrencyDataResponse
import com.ua.epam.data.state.network.model.TopCoinResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrencyService {
    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=true")
    fun getSimpleRequest(
        @Query("vs_currency") vcCurrency: String,
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = true,
    ): Observable<List<CurrencyDataResponse>>

    @GET("/api/v3/search/trending")
    fun getTopCoinsRequest(): Observable<List<TopCoinResponse>>
}