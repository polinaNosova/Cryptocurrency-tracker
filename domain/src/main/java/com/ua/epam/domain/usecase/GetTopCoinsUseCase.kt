package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.TopCoinsRepository

class GetTopCoinsUseCase(private val repository: TopCoinsRepository) {
    suspend fun execute() = repository.getTopCoins()
}