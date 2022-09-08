package com.ua.epam.ctiptocurrencytracker.model

import android.graphics.Color
import com.ua.epam.domain.model.*
import java.io.Serializable

data class CurrencyRateUiModel(
    val id: String,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double,
    val sparklineIn7dEntity: SparklineIn7dEntity,
    val lowTwentyFourHour: Double,
    val highTwentyFourHour: Double,
    val priceChangeResult: Double,
    val color: Int
) : Serializable {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}

object CurrencyUiMapper {
    fun toCurrencyUiModel(entities: List<FullCurrency>): List<CurrencyRateUiModel> {
        val uiCurrencyModels = mutableListOf<CurrencyRateUiModel>()
        for (entity in entities) {
            uiCurrencyModels.add(
                CurrencyRateUiModel(
                    entity.currencyEntity.id,
                    entity.currencyEntity.symbol,
                    entity.currencyEntity.image,
                    entity.currencyEntity.name,
                    entity.currencyEntity.currentPrice,
                    entity.sparkline_in_7d,
                    entity.lowTwentyFourHour,
                    entity.highTwentyFourHour,
                    entity.priceChangeResult,
                    if (entity.currencyEntity.currentPrice >= 0) Color.GREEN else Color.RED
                )
            )
        }
        return uiCurrencyModels
    }

    fun toItemUi(item: ItemEntity): ItemUIModel {
        return ItemUIModel(
            item.coin_id,
            item.id,
            item.large,
            item.market_cap_rank,
            item.name,
            item.price_btc,
            item.score,
            item.slug,
            item.small,
            item.symbol,
            item.thumb
        )
    }

    fun toListCoinUiModel(entities: List<TopCoinEntity>): List<TopCoinUiModel> {
        val uiCurrencyModels = mutableListOf<TopCoinUiModel>()
        for (entity in entities) {
            uiCurrencyModels.add(
                TopCoinUiModel(
                    toItemUi(entity.item)
                )
            )
        }
        return uiCurrencyModels
    }
}


