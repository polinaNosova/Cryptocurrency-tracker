package com.ua.epam.ctiptocurrencytracker.di

import com.ua.epam.data.repository.*
import com.ua.epam.data.storage.local.dao.CoinDao
import com.ua.epam.data.storage.local.database.CoinDB
import com.ua.epam.data.storage.network.api.CryptoCurrencyApi
import com.ua.epam.data.storage.network.api.RetrofitInstance
import com.ua.epam.domain.repository.CoinsDataRepository
import org.koin.dsl.module

val dataModule = module {
    single<CoinDao> {
        CoinDB.getInstance(context = get()).getDao()
    }
    single<CryptoCurrencyApi> {
        RetrofitInstance.currencyService
    }
    single<LocalCoinsDataSource> {
        RoomCoinsDataSource(dao = get())
    }
    single<NetworkCoinsDataSource> {
        RetrofitCoinsDataSource(api = get())
    }
    single<CoinsDataRepository> {
        CoinsDataRepositoryImpl(networkCoinsDataSource = get(), localCoinsDataSource = get())
    }
}