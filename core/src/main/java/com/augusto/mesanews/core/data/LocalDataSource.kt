package com.augusto.mesanews.core.data

import com.augusto.mesanews.core.domain.News

interface LocalDataSource {

    suspend fun favoriteNews(news: News): Result<Boolean>
}