package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.CoinsDataRepository

class GetCoinById(private val coinsDataRepository: CoinsDataRepository) {
    suspend fun execute(id: String) = coinsDataRepository.getCoinById(id)
}