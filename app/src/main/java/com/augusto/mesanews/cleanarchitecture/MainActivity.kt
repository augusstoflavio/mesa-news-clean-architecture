package com.augusto.mesanews.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.augusto.mesanews.cleanarchitecture.di.apiModule
import com.augusto.mesanews.cleanarchitecture.di.dataModule
import com.augusto.mesanews.cleanarchitecture.di.useCaseModule
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}