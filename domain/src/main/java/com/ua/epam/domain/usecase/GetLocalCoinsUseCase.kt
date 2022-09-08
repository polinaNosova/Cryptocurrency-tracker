package com.ua.epam.domain.usecase

import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.FullCurrency
import com.ua.epam.domain.repository.CurrencyDataRepository

class GetLocalCoinsUseCase(private val repository: CurrencyDataRepository) {
    suspend fun execute(): List<Currency> = repository.getAllCurrency()
}