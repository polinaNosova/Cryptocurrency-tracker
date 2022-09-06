package com.ua.epam.data.mapper

import com.ua.epam.data.state.local.model.CurrencyDbEntity
import com.ua.epam.domain.model.CurrencyEntity
import com.ua.epam.domain.model.SparklineIn7dEntity

object CurrencyLocalDataMapper {
    fun toCurrencyDbEntityList(list: List<CurrencyEntity>): List<CurrencyDbEntity> {
        return list.map {
            CurrencyDbEntity(
                it.id.toInt(),
                it.symbol,
                it.image,
                it.name,
                it.currentPrice,

                )
        }
    }

    fun toCurrencyDbEntity(item: CurrencyEntity): CurrencyDbEntity =
        CurrencyDbEntity(
            item.id.toInt(), item.symbol, item.image, item.name, item.currentPrice
        )

//    fun toCurrencyEntityList(list: List<CurrencyDbEntity>): List<CurrencyEntity> =
//        list.map {
//            CurrencyEntity(
//                it.id.toString(),
//                it.symbol,
//                it.image,
//                it.name,
//                it.currentPrice,
//
//                )
//        }
}