package com.augusto.mesanews.core.data.repository

import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.data.dataSource.RemoteDataSource
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.repository.NewsRepository

class NewsRepositoryImpl(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : NewsRepository {

    override suspend fun favorite(news: News): Result<Boolean> {
        return localDataSource.favoriteNews(news)
    }

    override suspend fun disfavor(news: News): Result<Boolean> {
        return localDataSource.disfavorNews(news)
    }

    override suspend fun get(currentPage: Int): Result<List<News>> {
        return remoteDataSource.getNews(currentPage)
    }

    override suspend fun getHighlights(): Result<List<News>> {
        return remoteDataSource.getHighlights()
    }

    override suspend fun getFavoriteNews(): Result<List<News>> {
        return localDataSource.getFavoriteNews()
    }

    override suspend fun checkIsFavorite(url: News): Result<Boolean> {
        return localDataSource.isFavorite(url)
    }
}