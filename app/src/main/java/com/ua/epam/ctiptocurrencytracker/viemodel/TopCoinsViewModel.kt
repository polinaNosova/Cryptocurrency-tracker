package com.ua.epam.ctiptocurrencytracker.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.ctiptocurrencytracker.model.TopCoinUiModel
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.usecase.GetTopCoinsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopCoinsViewModel(private val useCase: GetTopCoinsUseCase) : ViewModel() {
    private val _mapAction = MutableLiveData<List<TopCoinUiModel>>()

    val mapAction: LiveData<List<TopCoinUiModel>> get() = _mapAction

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    fun getTopCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.execute()) {
                is Result.Success -> _mapAction.postValue(
                    CurrencyUiMapper.toListCoinUiModel(
                        result.data
                    )
                )
                is Result.Error ->
                    _errorAction.postValue("An error occurred")
            }
        }
    }
}