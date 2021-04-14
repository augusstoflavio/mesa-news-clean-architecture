package com.augusto.mesanews.cleanarchitecture

import android.app.Application
import com.augusto.mesanews.cleanarchitecture.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    apiModule,
                    viewModelModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
    }
}