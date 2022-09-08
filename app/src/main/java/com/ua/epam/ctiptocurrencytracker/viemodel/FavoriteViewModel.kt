package com.ua.epam.ctiptocurrencytracker.viemodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.FullCurrency
import com.ua.epam.domain.usecase.GetLocalCoinsUseCase
import com.ua.epam.domain.usecase.SaveCoinsUseCase
import com.ua.epam.domain.usecase.ShowCurrencyDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val showCurrencyDataUseCase: ShowCurrencyDataUseCase,
    private val saveCoinsUseCase: SaveCoinsUseCase,
    private val getLocalCoinsUseCase: GetLocalCoinsUseCase, application: Application
) : AndroidViewModel(application) {

    private val _createMapAction = MutableLiveData<List<CurrencyRateUiModel>>()
    val createMapAction: LiveData<List<CurrencyRateUiModel>> get() = _createMapAction

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    @SuppressLint("CheckResult")
    fun getCurrencyRates() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = showCurrencyDataUseCase.execute()) {
                is Result.Success -> {
                    saveCoinsUseCase.execute(response.data)
                    _createMapAction.postValue(
                        CurrencyUiMapper.toCurrencyUiModel(response.data)
                    )
                }
                is Result.Error -> {
                    val list = getLocalCoinsUseCase.execute()
                    _createMapAction.postValue(
                        CurrencyUiMapper.toCurrencyUiModel(
                            emptyList()
                        )
                    )
                }
            }
        }
    }
}
