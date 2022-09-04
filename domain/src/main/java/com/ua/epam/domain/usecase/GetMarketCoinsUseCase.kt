package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.CoinTopMarketRepository

class GetMarketCoinsUseCase(private val repository: CoinTopMarketRepository) {
    fun execute(query: String) =
        repository.getCryptoCurrencyMarketList(query)
}