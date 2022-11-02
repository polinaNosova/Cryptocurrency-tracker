package com.ua.epam.ctiptocurrencytracker.viemodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.repository.CoinsDataRepositoryImpl
import com.ua.epam.data.repository.RetrofitCoinsDataSource
import com.ua.epam.data.repository.RoomCoinsDataSource
import com.ua.epam.data.storage.local.database.CoinDB
import com.ua.epam.data.storage.network.api.RetrofitInstance
import com.ua.epam.domain.usecase.AddLocalCoinUseCase
import com.ua.epam.domain.usecase.GetAllLocalCoinsUseCase
import com.ua.epam.domain.usecase.GetCoinsListUseCase

class MarketViewModelFactory(application: Application) : ViewModelProvider.Factory {
    private val dao = CoinDB.getInstance(application).getDao()
    private val remoteCoinsData =
        RetrofitCoinsDataSource(RetrofitInstance.currencyService)
    private val localCoinsDataSource = RoomCoinsDataSource(dao)
    private val repository = CoinsDataRepositoryImpl(remoteCoinsData, localCoinsDataSource)
    private val getCoinsListUseCase = GetCoinsListUseCase(repository)
    private val addLocalCoinUseCase = AddLocalCoinUseCase(repository)
    private val getAllLocalCoinsUseCase = GetAllLocalCoinsUseCase(repository)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MarketViewModel(
            getCoinsListUseCase, addLocalCoinUseCase, getAllLocalCoinsUseCase
        ) as T
    }
}