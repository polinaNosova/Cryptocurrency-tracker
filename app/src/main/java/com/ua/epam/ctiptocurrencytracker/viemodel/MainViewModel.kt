package com.ua.epam.ctiptocurrencytracker.viemodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjava.utils.SingleLiveEvent
import com.ua.epam.domain.usecase.GetMarketCoinsUseCase
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(private val currencyRateExecution: GetMarketCoinsUseCase) : ViewModel() {
    private val _mapAction = MutableLiveData<List<CurrencyRateUiModel>>()

    val mapAction: LiveData<List<CurrencyRateUiModel>> get() = _mapAction
    private val disposible = CompositeDisposable()

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    @SuppressLint("CheckResult")
    fun getCurrencyRates(query: String) {
        disposible.add(
            currencyRateExecution.execute(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _mapAction.postValue(CurrencyUiMapper.toCurrencyUiModel(it))
                }, { throwable ->
                    _errorAction.postValue(throwable.message)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }

    companion object {
        const val QUERY = "USD"
    }
}