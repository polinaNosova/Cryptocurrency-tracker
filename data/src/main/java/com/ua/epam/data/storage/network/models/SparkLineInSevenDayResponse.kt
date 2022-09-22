package com.ua.epam.data.storage.network.models

import com.ua.epam.domain.model.SparklineInSevenDays

data class SparkLineInSevenDayResponse(
    val price: ArrayList<Float>,
) {
    fun fromPrice(): SparklineInSevenDays {
        return SparklineInSevenDays(
            price
        )
    }
}
