package com.ua.epam.ctiptocurrencytracker.model

import android.graphics.Color
import com.ua.epam.domain.model.CurrencyEntity

data class CurrencyRateUiModel(
    val id: String,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double,
    val lowTwentyFourHour: Double,
    val highTwentyFourHour: Double,
    val priceChangeResult: Double,
    val color: Int
)

object CurrencyUiMapper {
    fun toCurrencyUiModel(entities: List<CurrencyEntity>): List<CurrencyRateUiModel> {
        val uiCurrencyModels = mutableListOf<CurrencyRateUiModel>()
        for (entity in entities) {
            uiCurrencyModels.add(
                CurrencyRateUiModel(
                    entity.id,
                    entity.symbol,
                    entity.image,
                    entity.name,
                    entity.currentPrice,
                    entity.lowTwentyFourHour,
                    entity.highTwentyFourHour,
                    entity.priceChangeResult,
                    if (entity.priceChangeResult >= 0) Color.GREEN else Color.RED
                )
            )
        }
        return uiCurrencyModels
    }
}


