package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.framework.UseCases
import com.augusto.mesanews.core.domain.useCase.*
import org.koin.dsl.module

val useCaseModule = module {

    single {
        UseCases(
            favoriteNews = FavoriteNews(get()),
            getHighlights = GetHighlights(get()),
            getNews = GetNews(get()),
            signin = Signin(get()),
            signup = Signup(get()),
        )
    }
}