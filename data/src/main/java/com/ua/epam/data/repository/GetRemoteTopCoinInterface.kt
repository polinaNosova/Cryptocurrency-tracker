package com.ua.epam.data.repository

import com.ua.epam.domain.model.TopCoinEntity
import io.reactivex.Observable

interface GetRemoteTopCoinInterface {
    fun getFindTopCoinsList(): Observable<List<TopCoinEntity>>
}