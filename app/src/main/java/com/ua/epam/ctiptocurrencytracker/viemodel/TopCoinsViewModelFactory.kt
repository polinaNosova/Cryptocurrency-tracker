package com.ua.epam.ctiptocurrencytracker.viemodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.state.remote.api.RetrofitInstance
import com.ua.epam.data.repository.GetRemoteTopCoinInterfaceImpl
import com.ua.epam.data.repository.GetTopCoinsListImpl
import com.ua.epam.domain.usecase.GetTopCoinsUseCase

class TopCoinsViewModelFactory() :
    ViewModelProvider.Factory {
    private val remoteCurrencyRates =
        GetRemoteTopCoinInterfaceImpl(RetrofitInstance.currencyService)
    private val repository = GetTopCoinsListImpl(remoteCurrencyRates)
    private val topCoinsUseCase = GetTopCoinsUseCase(repository)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopCoinsViewModel(
           topCoinsUseCase
        ) as T
    }
}