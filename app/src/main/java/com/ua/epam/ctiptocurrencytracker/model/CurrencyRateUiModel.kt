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
    val lowTwentyFourHour: Double,
    val highTwentyFourHour: Double,
    val priceChangeResult: Double,
    val sparkline_in_7d: ArrayList<Float>,
    val market_cap_rank: Int,
    val market_cap: Long,
    val color: Int,
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
    fun toCurrencyRateUiModels(entities: List<Currency>): List<CurrencyRateUiModel> {
        val uiCurrencyModels = mutableListOf<CurrencyRateUiModel>()
        for (entity in entities) {
            uiCurrencyModels.add(
                CurrencyRateUiModel(
                    entity.id,
                    entity.symbol,
                    entity.image,
                    entity.name,
                    entity.priceChangeResult,
                    entity.lowTwentyFourHour,
                    entity.highTwentyFourHour,
                    entity.priceChangeResult,
                    entity.sparkline_in_7d.price,
                    entity.market_cap_rank,
                    entity.market_cap,
                    if (entity.priceChangeResult >= 0) Color.GREEN else Color.RED
                )
            )
        }
        return uiCurrencyModels
    }

    fun currencyRateUiModelToCurrency(currencyRateUiModel: CurrencyRateUiModel): Currency {
        return Currency(
            currencyRateUiModel.id,
            currencyRateUiModel.symbol,
            currencyRateUiModel.image,
            currencyRateUiModel.name,
            currencyRateUiModel.currentPrice,
            currencyRateUiModel.lowTwentyFourHour,
            currencyRateUiModel.highTwentyFourHour,
            currencyRateUiModel.priceChangeResult,
            toSparkLineInSevenDays(currencyRateUiModel.sparkline_in_7d),
            currencyRateUiModel.market_cap_rank,
            currencyRateUiModel.market_cap
        )
    }

    fun toCurrencyRateUiModel(currency: Currency): CurrencyRateUiModel =
        CurrencyRateUiModel(
            currency.id,
            currency.symbol,
            currency.image,
            currency.name,
            currency.currentPrice,
            currency.lowTwentyFourHour,
            currency.highTwentyFourHour,
            currency.priceChangeResult,
            currency.sparkline_in_7d.price,
            currency.market_cap_rank,
            currency.market_cap,
            if (currency.priceChangeResult >= 0) Color.GREEN else Color.RED
        )

    fun toSparkLineInSevenDays(sparkline_in_7d: ArrayList<Float>): SparklineInSevenDays {
        return SparklineInSevenDays(
            sparkline_in_7d
        )
    }
}


