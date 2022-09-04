package com.ua.epam.data.remote.api

import com.ua.epam.data.remote.dto.HomeModelResponse
import com.ua.epam.data.remote.dto.MarketModelResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/api/v3/coins/markets")
   fun getSimpleRequest(
        @Query("vs_currency") vcCurrency: String,
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 100,
        @Query("per_page") page: Int = 1,
        @Query("per_page") sparkline: Boolean = false,
    ):Observable<List<MarketModelResponse>>

   @GET("/data-api/v3/cryptocurrency/listing?start=1&limit=100")
   fun getRequest():Observable<List<HomeModelResponse>>
}