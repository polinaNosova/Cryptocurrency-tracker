package com.ua.epam.ctiptocurrencytracker.model

import android.graphics.Color
import com.ua.epam.domain.model.CurrencyEntity
import com.ua.epam.domain.model.ItemEntity
import com.ua.epam.domain.model.SparklineIn7dEntity
import com.ua.epam.domain.model.TopCoinEntity
import java.io.Serializable

data class CurrencyRateUiModel(
    val id: String,
    val symbol: String,
    val image: String,
    val name: String,
    val currentPrice: Double,
    val lowTwentyFourHour: Double,
    val highTwentyFourHour: Double,
    val priceChangeResult: Double,
    val sparklineIn7dEntity: SparklineIn7dEntity,
    val market_cap_rank: Int,
    val circulating_supply: Double,
    val ath_date: String,
    val market_cap: Long,
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
                    entity.sparkline_in_7d,
                    entity.market_cap_rank,
                    entity.circulating_supply,
                    entity.ath_date,
                    entity.market_cap,
                    if (entity.priceChangeResult >= 0) Color.GREEN else Color.RED
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


