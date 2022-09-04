package com.ua.epam.domain.usecase

import com.ua.epam.domain.repository.CoinHomeFragmentRepository

class GetHomeTopCoinsUseCase(private val repository: CoinHomeFragmentRepository) {
    fun execute(query: String) =
        repository.getCryptoCurrencyTopMarketList(query)
}