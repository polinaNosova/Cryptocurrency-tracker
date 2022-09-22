package com.ua.epam.ctiptocurrencytracker.di

import com.ua.epam.domain.usecase.AddLocalCoinUseCase
import com.ua.epam.domain.usecase.DeleteLocalCoinUseCase
import com.ua.epam.domain.usecase.GetAllLocalCoinsUseCase
import com.ua.epam.domain.usecase.GetCoinsListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCoinsListUseCase>{
        GetCoinsListUseCase(coinsDataRepository = get())
    }
    factory<GetAllLocalCoinsUseCase>{
        GetAllLocalCoinsUseCase(coinsDataRepository = get())
    }
    factory<AddLocalCoinUseCase>{
        AddLocalCoinUseCase(coinsDataRepository = get())
    }
    factory<AddLocalCoinUseCase>{
        AddLocalCoinUseCase(coinsDataRepository = get())
    }
    factory<DeleteLocalCoinUseCase>{
        DeleteLocalCoinUseCase(coinsDataRepository = get())
    }
}