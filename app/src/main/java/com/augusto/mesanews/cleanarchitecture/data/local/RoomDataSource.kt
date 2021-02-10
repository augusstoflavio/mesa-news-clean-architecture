package com.augusto.mesanews.cleanarchitecture.data.local

import com.augusto.mesanews.core.data.dataSource.LocalDataSource
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News

class RoomDataSource: LocalDataSource {

    override suspend fun favoriteNews(news: News): Result<Boolean> {
        TODO("Not yet implemented")
    }
}