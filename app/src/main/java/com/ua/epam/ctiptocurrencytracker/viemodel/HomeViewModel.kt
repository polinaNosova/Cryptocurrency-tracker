package com.ua.epam.ctiptocurrencytracker.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.usecase.ShowCurrencyDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: ShowCurrencyDataUseCase) : ViewModel() {
    private val _mapAction = MutableLiveData<List<CurrencyRateUiModel>>()

    val mapAction: LiveData<List<CurrencyRateUiModel>> get() = _mapAction

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    fun getCurrencyRates() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.execute()) {
                is Result.Success -> _mapAction.postValue(
                    CurrencyUiMapper.toCurrencyUiModel(
                        result.data
                    )
                )
                is Result.Error ->
                    _errorAction.postValue("An error occurred")
            }
        }
    }
}