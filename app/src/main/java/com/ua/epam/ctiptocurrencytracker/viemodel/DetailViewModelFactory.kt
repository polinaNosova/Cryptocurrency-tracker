package com.ua.epam.ctiptocurrencytracker.viemodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.repository.CoinsDataRepositoryImpl
import com.ua.epam.data.repository.RetrofitCoinsDataSource
import com.ua.epam.data.repository.RoomCoinsDataSource
import com.ua.epam.data.storage.local.database.CoinDB
import com.ua.epam.data.storage.network.api.RetrofitInstance
import com.ua.epam.domain.usecase.AddLocalCoinUseCase
import com.ua.epam.domain.usecase.GetCoinsListUseCase

class DetailViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    private val dao = CoinDB.getInstance(application).getDao()
    private val remoteCoinsData =
        RetrofitCoinsDataSource(RetrofitInstance.currencyService)
    private val localCoinsDataSource = RoomCoinsDataSource(dao)
    private val coinsDataRepositoryImpl =
        CoinsDataRepositoryImpl(remoteCoinsData, localCoinsDataSource)
    private val addLocalCoinUseCase = AddLocalCoinUseCase(coinsDataRepositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(
            addLocalCoinUseCase) as T
    }
}