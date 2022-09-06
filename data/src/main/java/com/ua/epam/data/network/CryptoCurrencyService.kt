package com.ua.epam.data.network

import com.ua.epam.data.model.CurrencyDataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrencyService {
    @GET("/api/v3/coins/markets")
    fun getSimpleRequest(
        @Query("vs_currency") vcCurrency: String,
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = true,
    ): Observable<List<CurrencyDataResponse>>
}