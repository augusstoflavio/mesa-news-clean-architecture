package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.app.Application
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.Preferences
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedDataSourceImpl
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinTestApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val baseModule = module {
            single<SharedPreferencesDataSource> {
                SharedDataSourceImpl(Preferences(androidContext()))
            }
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@KoinTestApp)
            modules(listOf(baseModule))
        }
    }

    internal fun injectModule(module: Module) {
        loadKoinModules(module)
    }
}