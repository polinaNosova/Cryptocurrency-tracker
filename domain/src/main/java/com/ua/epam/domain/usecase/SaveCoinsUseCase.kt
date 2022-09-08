package com.ua.epam.domain.usecase

import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.FullCurrency
import com.ua.epam.domain.repository.CurrencyDataRepository

class SaveCoinsUseCase(private val repository: CurrencyDataRepository) {
    suspend fun execute(list: List<FullCurrency>) {
        if (repository.getAllCurrency() == emptyList<FullCurrency>()) {
            list.forEach { repository.insert(it.currencyEntity) }
        } else {
            repository.update(list.map { it.currencyEntity })
        }
    }
}