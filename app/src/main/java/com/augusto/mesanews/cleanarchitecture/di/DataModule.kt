package com.augusto.mesanews.cleanarchitecture.di

import androidx.room.Room
import com.augusto.mesanews.cleanarchitecture.App
import com.augusto.mesanews.cleanarchitecture.data.api.ApiDataSource
import com.augusto.mesanews.cleanarchitecture.data.local.RoomDataBase
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.Preferences
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.RoomDataSource
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedDataSourceImpl
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.data.dataSource.RemoteDataSource
import com.augusto.mesanews.core.data.repository.AuthRepositoryImpl
import com.augusto.mesanews.core.data.repository.NewsRepositoryImpl
import com.augusto.mesanews.core.domain.repository.AuthRepository
import com.augusto.mesanews.core.domain.repository.NewsRepository
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val PREFERENCES = StringQualifier("preferences")

val dataModule = module {

    single {
        Room.databaseBuilder(
            App.instance,
            RoomDataBase::class.java,
            "mesa-news"
        ).build()
    }

    single<RemoteDataSource> {
        ApiDataSource(get(), get())
    }

    single<LocalDataSource> {
        RoomDataSource(get())
    }

    single(PREFERENCES) {
        Preferences(App.instance)
    }

    single<SharedPreferencesDataSource> {
        SharedDataSourceImpl(get(PREFERENCES))
    }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}