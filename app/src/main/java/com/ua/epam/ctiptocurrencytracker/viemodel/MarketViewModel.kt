package com.ua.epam.ctiptocurrencytracker.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.usecase.AddLocalCoinUseCase
import com.ua.epam.domain.usecase.GetCoinsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val addLocalCoinUseCase: AddLocalCoinUseCase,
) : ViewModel() {
    private val _liveData = MutableLiveData<List<CurrencyRateUiModel>>()
    val liveData: LiveData<List<CurrencyRateUiModel>> get() = _liveData

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    fun getCoinsList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getCoinsListUseCase.execute()) {
                is Result.Success -> {
                    _liveData.postValue(
                        CurrencyUiMapper.toCurrencyRateUiModels(
                            result.data
                        )
                    )
                }
                is Result.Error ->
                    _errorAction.postValue("An error occurred")
            }
        }
    }
}



