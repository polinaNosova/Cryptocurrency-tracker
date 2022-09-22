package com.ua.epam.domain.usecase

import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.repository.CoinsDataRepository

class DeleteLocalCoinUseCase(private val coinsDataRepository: CoinsDataRepository) {
    suspend fun execute(currency: Currency) = coinsDataRepository.deleteLocalCoin(currency)
}