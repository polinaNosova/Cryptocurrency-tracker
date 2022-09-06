package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.GetTopCoinsRepository

class GetTopCoinsUseCase(private val reposotory: GetTopCoinsRepository) {
    fun execute() = reposotory.getCryptoCurrencyMarketList()
}