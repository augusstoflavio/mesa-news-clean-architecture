package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.data.api.ApiDataSource
import com.augusto.mesanews.cleanarchitecture.data.local.RoomDataSource
import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.data.dataSource.RemoteDataSource
import com.augusto.mesanews.core.data.repository.AuthRepositoryImpl
import com.augusto.mesanews.core.data.repository.NewsRepositoryImpl
import com.augusto.mesanews.core.domain.repository.AuthRepository
import com.augusto.mesanews.core.domain.repository.NewsRepository
import org.koin.dsl.module

val dataModule = module {

    single<RemoteDataSource> {
        ApiDataSource()
    }

    single<LocalDataSource> {
        RoomDataSource()
    }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}