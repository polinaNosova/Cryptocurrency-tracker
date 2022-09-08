package com.ua.epam.ctiptocurrencytracker.viemodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.state.remote.api.RetrofitInstance
import com.ua.epam.data.repository.CurrencyDataRepositoryImpl
import com.ua.epam.data.repository.RemoteCurrencyInterfaceImpl
import com.ua.epam.data.state.remote.local.db.CoinsDb
import com.ua.epam.domain.usecase.ShowCurrencyDataUseCase

class HomeViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    private val dao = CoinsDb.getDatabase(application).getCurrencyDao()
    private val remoteCurrencyRates =
        RemoteCurrencyInterfaceImpl(RetrofitInstance.currencyService,dao)
    private val repository = CurrencyDataRepositoryImpl(remoteCurrencyRates)
    private val currencyRateUseCase = ShowCurrencyDataUseCase(repository)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            currencyRateUseCase
        ) as T
    }
}