package com.ua.epam.ctiptocurrencytracker.viemodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.domain.usecase.GetCurrencyDataUseCase
import com.ua.epam.data.network.RetrofitInstance
import com.ua.epam.data.repository.GetCurrencyDataRepositoryImpl
import com.ua.epam.data.repository.GetRemoteCurrencyInterfaceImpl

class MarketViewModelFactory : ViewModelProvider.Factory {

    private val remoteCurrencyRates = GetRemoteCurrencyInterfaceImpl(RetrofitInstance.currencyService)
    private val repository = GetCurrencyDataRepositoryImpl(remoteCurrencyRates)
    private val currencyRateUseCase = GetCurrencyDataUseCase(repository)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MarketViewModel(
            currencyRateUseCase
        ) as T
    }
}