package com.ua.epam.ctiptocurrencytracker.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.usecase.AddLocalCoinUseCase
import com.ua.epam.domain.usecase.GetCoinsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val addLocalCoinUseCase: AddLocalCoinUseCase,
    private val getCoinsListUseCase: GetCoinsListUseCase,
) : ViewModel() {
    private val _mapActionFullCurrency = MutableLiveData<List<CurrencyRateUiModel>>()
    val mapActionFullCurrency: LiveData<List<CurrencyRateUiModel>> get() = _mapActionFullCurrency

    private val _mapActionCurrency = MutableLiveData<List<CurrencyRateUiModel>>()
    val mapActionCurrency: LiveData<List<CurrencyRateUiModel>> get() = _mapActionCurrency

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    fun addCoinToLocalStorage(currency: CurrencyRateUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getCoinsListUseCase.execute()) {
                is Result.Success -> {
                    _mapActionFullCurrency.postValue(
                        CurrencyUiMapper.toCurrencyRateUiModels(
                            result.data
                        )
                    )
                    for (i in result.data) {
                        addLocalCoinUseCase.execute(i)
                    }
                }
                is Result.Error ->
                    _errorAction.postValue("An error occurred")
            }
        }
    }
}