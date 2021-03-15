package com.augusto.mesanews.cleanarchitecture.data.local.dataSouce

import com.augusto.mesanews.cleanarchitecture.data.local.Database
import com.augusto.mesanews.cleanarchitecture.data.local.helper.safeQuery
import com.augusto.mesanews.cleanarchitecture.data.local.model.FavoriteNewsConverter
import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result

class RoomDataSource(val database: Database): LocalDataSource {

    override suspend fun favoriteNews(news: News): Result<Boolean> = safeQuery {
        database.favoriteNewsDao().save(
                FavoriteNewsConverter.fromNews(news)
        )
        return@safeQuery true
    }

    override suspend fun disfavorNews(news: News): Result<Boolean> = safeQuery {
        database.favoriteNewsDao().delete(
                FavoriteNewsConverter.fromNews(news)
        )
        return@safeQuery true
    }

    override suspend fun isFavorite(news: News): Result<Boolean> = safeQuery {
        return@safeQuery database.favoriteNewsDao().getByUrl(news.url).isNotEmpty()
    }
}