package com.augusto.mesanews.core.interactors

import com.augusto.mesanews.core.data.LocalDataSource
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class FavoriteNews(private val localDataSource: LocalDataSource) {

    suspend operator fun invoke(news: News): Result<Boolean> {
        return localDataSource.favoriteNews(news)
    }
}