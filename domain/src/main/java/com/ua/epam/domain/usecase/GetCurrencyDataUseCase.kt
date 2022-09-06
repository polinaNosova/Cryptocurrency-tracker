package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.GetCurrencyDataRepository

class GetCurrencyDataUseCase(private val repository: GetCurrencyDataRepository) {
    fun execute(query: String) =
        repository.getCryptoCurrencyMarketList(query)
}