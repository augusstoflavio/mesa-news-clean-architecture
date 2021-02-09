package com.augusto.mesanews.core.data

import com.augusto.mesanews.core.domain.News

class NewsRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    suspend fun favorite(news: News): Result<Boolean> {
        return localDataSource.favoriteNews(news)
    }

    suspend fun get(currentPage: Int): Result<List<News>> {
        return remoteDataSource.getNews(currentPage)
    }

    suspend fun getHighlights(): Result<List<News>> {
        return remoteDataSource.getHighlights()
    }
}