package com.augusto.mesanews.cleanarchitecture

import android.app.Application
import com.augusto.mesanews.cleanarchitecture.di.apiModule
import com.augusto.mesanews.cleanarchitecture.di.dataModule
import com.augusto.mesanews.cleanarchitecture.di.useCaseModule
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
            modules(
                listOf(
                    dataModule,
                    useCaseModule,
                    apiModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
    }
}