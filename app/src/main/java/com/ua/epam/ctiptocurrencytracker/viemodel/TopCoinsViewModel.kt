package com.ua.epam.ctiptocurrencytracker.viemodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjava.utils.SingleLiveEvent
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.ctiptocurrencytracker.model.TopCoinUiModel
import com.ua.epam.domain.usecase.GetTopCoinsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class TopCoinsViewModel(private val useCase: GetTopCoinsUseCase) : ViewModel() {
    private val _mapAction = MutableLiveData<List<TopCoinUiModel>>()

    val mapAction: LiveData<List<TopCoinUiModel>> get() = _mapAction
    private val disposible = CompositeDisposable()

    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    @SuppressLint("CheckResult")
    fun getTopCoins() {
        disposible.add(
            useCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _mapAction.postValue(CurrencyUiMapper.toListCoinUiModel(it))
                }, { throwable ->
                    _errorAction.postValue(throwable.message)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }

}