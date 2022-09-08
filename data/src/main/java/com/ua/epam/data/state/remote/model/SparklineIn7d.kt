package com.ua.epam.data.state.remote.model

import com.ua.epam.domain.model.SparklineIn7dEntity

data class SparklineIn7d(
    val price: List<Float>
) {
    fun toSparklineIn7dEntity(): SparklineIn7dEntity {
        return SparklineIn7dEntity(price)
    }
}