package com.augusto.mesanews.cleanarchitecture.framework

import com.augusto.mesanews.core.domain.useCase.*

data class UseCases (
        val getHighlights: GetHighlights,
        val getNews: GetNews,
        val favoriteNews: FavoriteNews,
        val signin: Signin,
        val signup: Signup,
)