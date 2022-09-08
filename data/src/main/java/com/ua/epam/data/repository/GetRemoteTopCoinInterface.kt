package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.TopCoinEntity
interface GetRemoteTopCoinInterface {
    suspend fun getFindTopCoinsList(): Result<List<TopCoinEntity>>
}