package com.ua.epam.domain.model

data class CryptoCurrencyEntity(
    val quotes: List<QuotyEntity>,
    val symbol: String,
    val slug: String,
)
