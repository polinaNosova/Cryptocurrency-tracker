package com.ua.epam.data.repository

import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.TopCoinEntity
import com.ua.epam.domain.repository.TopCoinsRepository
import io.reactivex.Observable

class GetTopCoinsListImpl(private val getRemoteTopCoinInterface: GetRemoteTopCoinInterface) :
    TopCoinsRepository {
    override suspend fun getTopCoins():Result<List<TopCoinEntity>> {
        return getRemoteTopCoinInterface.getFindTopCoinsList()
    }
}