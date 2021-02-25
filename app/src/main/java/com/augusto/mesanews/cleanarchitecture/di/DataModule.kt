package com.augusto.mesanews.cleanarchitecture.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.augusto.mesanews.cleanarchitecture.data.api.ApiDataSource
import com.augusto.mesanews.cleanarchitecture.data.local.Database
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
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val PREFERENCES = StringQualifier("preferences")
private val ROOM_DATABASE = StringQualifier("roomdatabase")

val dataModule = module {

    single(ROOM_DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            Database::class.java,
            "mesa-news"
        ).build()
    }

    single<RemoteDataSource> {
        ApiDataSource(get(), get())
    }

    single<LocalDataSource> {
        val room = get<Database>(ROOM_DATABASE)
        RoomDataSource(room)
    }

    single(PREFERENCES) {
        Preferences(androidContext())
    }

    single<SharedPreferencesDataSource> {
        SharedDataSourceImpl(get(PREFERENCES))
    }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}