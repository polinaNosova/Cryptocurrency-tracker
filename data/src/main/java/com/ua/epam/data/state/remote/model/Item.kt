package com.ua.epam.data.state.remote.model

import com.google.gson.annotations.SerializedName
import com.ua.epam.domain.model.ItemEntity

data class Item(
    @SerializedName("coin_id")
    val coinId: Int,
    val id: String,
    val large: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    val name: String,
    @SerializedName("price_btc")
    val priceBtc: Double,
    val score: Int,
    val slug: String,
    val small: String,
    val symbol: String,
    val thumb: String
) {
    fun toItemEntity(): ItemEntity {
        return ItemEntity(
            coinId,
            id,
            large,
            marketCapRank,
            name,
            priceBtc,
            score,
            slug,
            small,
            symbol,
            thumb
        )

    }
}