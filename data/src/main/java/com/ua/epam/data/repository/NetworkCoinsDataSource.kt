package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.Currency
interface NetworkCoinsDataSource {
    suspend fun getCoinsList():Result<List<Currency>>
}