package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.core.domain.useCase.*
import org.koin.dsl.module

val useCaseModule = module {

    single {
        UseCases(
            favoriteNews = FavoriteNews(get()),
            disfavorNews = DisfavorNews(get()),
            getHighlights = GetHighlights(get()),
            getFavoriteNews = GetFavoriteNews(get()),
            getNews = GetNews(get()),
            signin = Signin(get()),
            signup = Signup(get()),
        )
    }
}