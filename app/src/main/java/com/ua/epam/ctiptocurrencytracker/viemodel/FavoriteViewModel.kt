package com.ua.epam.ctiptocurrencytracker.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val deleteLocalCoinUseCase: DeleteLocalCoinUseCase,
    private val getAllLocalCoins: GetAllLocalCoinsUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _liveData = MutableLiveData<List<CurrencyRateUiModel>>()
    val liveData: LiveData<List<CurrencyRateUiModel>> get() = _liveData

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    fun deleteCoin(currencyRateUiModel: CurrencyRateUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteLocalCoinUseCase.execute(CurrencyUiMapper.currencyRateUiModelToCurrency(
                currencyRateUiModel))
        }
    }
    fun getLocalList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getAllLocalCoins.execute()
            _liveData.postValue(CurrencyUiMapper.toCurrencyRateUiModels(list))
        }
    }
}
