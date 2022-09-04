package com.ua.epam.domain.model

data class QuotyEntity(
    val price: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange60d: Double,
    val percentChange7d: Double,
)
