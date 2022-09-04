package com.ua.epam.ctiptocurrencytracker.viemodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.remote.api.RetrofitClient
import com.ua.epam.data.repository.CoinHomeFragmentRepositoryImpl
import com.ua.epam.data.repository.RemoteCryptoDataSourceImpl
import com.ua.epam.domain.usecase.GetHomeTopCoinsUseCase

class HomeViewModelFactory() :
    ViewModelProvider.Factory {
    private val remoteCurrencyRates = RemoteCryptoDataSourceImpl(RetrofitClient.currencyService)
    private val repository = CoinHomeFragmentRepositoryImpl(remoteCurrencyRates)
    private val currencyRateUseCase = GetHomeTopCoinsUseCase(repository)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            currencyRateUseCase
        ) as T
    }
}