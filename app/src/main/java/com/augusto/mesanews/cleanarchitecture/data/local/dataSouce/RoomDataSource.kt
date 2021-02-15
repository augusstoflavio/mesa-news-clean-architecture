package com.augusto.mesanews.cleanarchitecture.data.local.dataSouce

import com.augusto.mesanews.cleanarchitecture.data.local.RoomDataBase
import com.augusto.mesanews.cleanarchitecture.data.local.helper.safeQuery
import com.augusto.mesanews.cleanarchitecture.data.local.model.FavoriteNews
import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result

class RoomDataSource(val roomDataBase: RoomDataBase): LocalDataSource {

    override suspend fun favoriteNews(news: News): Result<Boolean> = safeQuery {
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
        return@safeQuery true
    }
}