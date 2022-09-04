package com.ua.epam.data.mapper

import com.ua.epam.data.remote.dto.Quote
import com.ua.epam.domain.model.QuotyEntity

object QuetyResponseMapper {
    fun toQuetyEntity(list: List<Quote>): List<QuotyEntity> =
        list.map {
            QuotyEntity(
                it.price,
                it.percentChange1h,
                it.percentChange24h,
                it.percentChange60d,
                it.percentChange7d
            )
        }
    }
