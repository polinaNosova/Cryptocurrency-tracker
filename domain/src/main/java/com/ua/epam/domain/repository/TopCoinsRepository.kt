package com.ua.epam.domain.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.TopCoinEntity
import io.reactivex.Observable

interface TopCoinsRepository {
    suspend fun getTopCoins():Result<List<TopCoinEntity>>
}