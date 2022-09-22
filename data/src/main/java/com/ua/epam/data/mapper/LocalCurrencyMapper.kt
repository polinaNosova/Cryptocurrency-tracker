package com.ua.epam.data.mapper

import com.ua.epam.data.storage.local.entities.CoinEntity
import com.ua.epam.domain.model.Currency
import com.ua.epam.domain.model.SparklineInSevenDays

object LocalCurrencyMapper {
    fun mapEntitiesToCurrencies(list: List<CoinEntity>): List<Currency> =
        list.map {
            Currency(
                it.id,
                it.symbol,
                it.image,
                it.name,
                it.currentPrice,
                it.lowTwentyFourH,
                it.highTwentyFourHour,
                it.priceChangeTwentyFourH,
                toSparklineInSevenDays(it.sparklineInSevenD),
                it.marketCapRank,
                it.marketCap
            )
        }

    fun mapCurrencyToEntity(currency: Currency): CoinEntity {
        return CoinEntity(
            currency.id,
            currency.symbol,
            currency.image,
            currency.name,
            currency.currentPrice,
            currency.highTwentyFourHour,
            currency.lowTwentyFourHour,
            currency.market_cap,
            currency.market_cap_rank,
            currency.priceChangeResult,
            toSparklineInSevenDays(currency.sparkline_in_7d)
        )
    }

    fun toSparklineInSevenDays(sparklineInSevenDays: ArrayList<Float>): SparklineInSevenDays {
        return SparklineInSevenDays(
            sparklineInSevenDays
        )
    }

    fun toSparklineInSevenDays(sparklineInSevenDays: SparklineInSevenDays): ArrayList<Float> {
        return sparklineInSevenDays.price
    }

    fun mapEntityToCurrency(entity: CoinEntity): Currency =
        Currency(entity.id,
            entity.symbol,
            entity.image,
            entity.name,
            entity.currentPrice,
            entity.highTwentyFourHour,
            entity.lowTwentyFourH,
            entity.priceChangeTwentyFourH,
            toSparklineInSevenDays(entity.sparklineInSevenD),
            entity.marketCapRank,
            entity.marketCap
        )
}
