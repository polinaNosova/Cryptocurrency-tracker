package com.ua.epam.data.state.network.model

import com.ua.epam.domain.model.TopCoinEntity

data class TopCoinResponse(
    val item: Item
) {
    fun toTopCoinEntity(): TopCoinEntity {
        return TopCoinEntity(item.toItemEntity())
    }
}