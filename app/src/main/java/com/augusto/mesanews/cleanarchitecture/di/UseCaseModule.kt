package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.core.domain.useCase.*
import org.koin.dsl.module

val useCaseModule = module {

    single {
        DisfavorNews(get())
    }

    single {
        FavoriteNews(get())
    }

    single {
        GetFavoriteNews(get())
    }

    single {
        GetHighlights(get())
    }

    single {
        GetNews(get())
    }

    single {
        Signin(get())
    }

    single {
        Signup(get())
    }
}