package com.ua.epam.ctiptocurrencytracker.viemodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ua.epam.data.repository.CurrencyDataRepositoryImpl
import com.ua.epam.data.repository.RemoteCurrencyInterfaceImpl
import com.ua.epam.data.state.remote.api.RetrofitInstance
import com.ua.epam.data.state.remote.local.db.CoinsDb
import com.ua.epam.domain.usecase.GetLocalCoinsUseCase
import com.ua.epam.domain.usecase.SaveCoinsUseCase
import com.ua.epam.domain.usecase.ShowCurrencyDataUseCase

class FavoriteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    private val dao = CoinsDb.getDatabase(application).getCurrencyDao()
    private val remoteCurrencyInterfaceImpl =
        RemoteCurrencyInterfaceImpl(RetrofitInstance.currencyService, dao)
    private val repository = CurrencyDataRepositoryImpl(remoteCurrencyInterfaceImpl)
    private val showCurrencyDataUseCase = ShowCurrencyDataUseCase(repository)
    private val saveCoinsUseCase = SaveCoinsUseCase(repository)
    private val getLocalCoinsUseCase = GetLocalCoinsUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(
            showCurrencyDataUseCase, saveCoinsUseCase, getLocalCoinsUseCase,application
        ) as T
    }
}