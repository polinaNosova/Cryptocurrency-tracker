package com.ua.epam.data.repository

import com.ua.epam.data.mapper.CurrencyResponseMapper
import com.ua.epam.data.state.remote.api.CryptoCurrencyService
import com.ua.epam.domain.common.Result
import com.ua.epam.domain.model.TopCoinEntity

class GetRemoteTopCoinInterfaceImpl(private val service: CryptoCurrencyService) :
    GetRemoteTopCoinInterface {
    override suspend fun getFindTopCoinsList(): Result<List<TopCoinEntity>> {
        return try {
            val response = service.getTopCoinsRequest()
            if (response.isSuccessful) {
                Result.Success(CurrencyResponseMapper.toCoinTopEntity(response.body()!!))
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}