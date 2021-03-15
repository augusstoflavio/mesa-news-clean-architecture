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
        val result = remoteDataSource.getNews(currentPage)
        if (result is Result.Success) {
            return Result.Success(fillFavorite(result.data))
        }
        return result
    }

    override suspend fun getHighlights(): Result<List<News>> {
        val result = remoteDataSource.getHighlights()
        if (result is Result.Success) {
            return Result.Success(fillFavorite(result.data))
        }
        return result
    }

    private suspend fun fillFavorite(news: List<News>): List<News> {
        return news.map {
            it.favorite = checkIsFavorite(it)
            return@map it
        }
    }

    private suspend fun checkIsFavorite(url: News): Boolean {
        val result = localDataSource.isFavorite(url)
        return result is Result.Success && result.data
    }
}