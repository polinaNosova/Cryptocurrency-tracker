package com.ua.epam.ctiptocurrencytracker.viemodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.data.repository.CoinsDataRepositoryImpl
import com.ua.epam.data.repository.RetrofitCoinsDataSource
import com.ua.epam.data.repository.RoomCoinsDataSource
import com.ua.epam.data.storage.local.database.CoinDB
import com.ua.epam.data.storage.network.api.RetrofitInstance
import com.ua.epam.domain.usecase.GetCoinsListUseCase

class TopGainViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    private val dao = CoinDB.getInstance(application).getDao()
    private val remoteCoinsData =
        RetrofitCoinsDataSource(RetrofitInstance.currencyService)
    private val localCoinsDataSource = RoomCoinsDataSource(dao)
    private val repository = CoinsDataRepositoryImpl(remoteCoinsData, localCoinsDataSource)
    private val getCoinsListUseCase = GetCoinsListUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopGainViewModel(
            getCoinsListUseCase
        ) as T
    }
}