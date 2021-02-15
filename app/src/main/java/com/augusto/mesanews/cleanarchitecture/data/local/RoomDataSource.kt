package com.augusto.mesanews.cleanarchitecture.data.local

import com.augusto.mesanews.cleanarchitecture.data.local.model.FavoriteNews
import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News

class RoomDataSource(val roomDataBase: RoomDataBase): LocalDataSource {

    override suspend fun favoriteNews(news: News): Result<Boolean> {
        try {
            roomDataBase.favoriteNewsDao().save(
                FavoriteNews(
                    id = null,
                    title = news.title,
                    description = news.description,
                    content = news.content,
                    author = news.author,
                    publishedAt = news.publishedAt,
                    url = news.url,
                    imageUrl = news.imageUrl
                )
            )
            return Result.Success(true)
        } catch (e: Exception) {
            return Result.Failure(
                Result.Error(message = "Database error")
            )
        }
    }
}