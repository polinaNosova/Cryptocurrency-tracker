package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.CoinsDataRepository

class GetAllLocalCoinsUseCase(private val coinsDataRepository: CoinsDataRepository) {
    suspend fun execute() = coinsDataRepository.getAllLocalCoins()
}