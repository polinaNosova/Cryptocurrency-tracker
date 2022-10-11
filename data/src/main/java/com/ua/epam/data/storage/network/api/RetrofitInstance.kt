package com.ua.epam.data.storage.network.api

import com.ua.epam.domain.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val currencyService: CryptoCurrencyApi by lazy { retrofit.create(CryptoCurrencyApi::class.java) }
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

     val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}


