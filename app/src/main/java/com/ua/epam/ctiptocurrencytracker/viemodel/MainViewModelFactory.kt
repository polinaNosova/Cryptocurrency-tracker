package com.ua.epam.ctiptocurrencytracker.viemodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.repository.CoinHomeFragmentRepositoryImpl
import com.ua.epam.data.repository.RemoteCryptoDataSourceImpl
import com.ua.epam.domain.usecase.GetMarketCoinsUseCase
import com.ua.epam.data.remote.api.RetrofitClient
import com.ua.epam.data.repository.CoinTopMarketRepositoryImpl

class MainViewModelFactory : ViewModelProvider.Factory {

    private val remoteCurrencyRates = RemoteCryptoDataSourceImpl(RetrofitClient.currencyService)
    private val repository = CoinTopMarketRepositoryImpl(remoteCurrencyRates)
    private val currencyRateUseCase = GetMarketCoinsUseCase(repository)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            currencyRateUseCase
        ) as T
    }
}