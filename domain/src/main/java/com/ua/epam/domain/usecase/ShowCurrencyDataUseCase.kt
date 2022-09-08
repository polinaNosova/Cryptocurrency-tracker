package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.CurrencyDataRepository

class ShowCurrencyDataUseCase(private val repository: CurrencyDataRepository) {
   suspend fun execute() =
        repository.getCryptoCurrencyMarketList()
}