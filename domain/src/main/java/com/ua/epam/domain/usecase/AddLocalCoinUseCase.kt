package com.ua.epam.domain.usecase

import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.repository.CoinsDataRepository

class AddLocalCoinUseCase(private val coinsDataRepository: CoinsDataRepository) {
    suspend fun execute(fullCurrency: Currency) {
        if (coinsDataRepository.getCoinsList() != emptyList<Currency>()) {
           coinsDataRepository.addCoin(fullCurrency)
        }
    }
}