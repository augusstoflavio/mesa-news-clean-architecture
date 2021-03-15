package com.augusto.mesanews.cleanarchitecture.data

import com.augusto.mesanews.core.domain.useCase.*

data class UseCases (
        val getHighlights: GetHighlights,
        val getNews: GetNews,
        val favoriteNews: FavoriteNews,
        val disfavorNews: DisfavorNews,
        val signin: Signin,
        val signup: Signup,
)