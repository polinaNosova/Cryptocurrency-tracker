package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.CoinsDataRepository

class GetCoinsListUseCase(private val coinsDataRepository: CoinsDataRepository) {
    suspend fun execute() = coinsDataRepository.getCoinsList()
}