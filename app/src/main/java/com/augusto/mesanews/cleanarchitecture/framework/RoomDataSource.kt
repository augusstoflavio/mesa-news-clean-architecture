package com.augusto.mesanews.cleanarchitecture.framework

import com.augusto.mesanews.core.data.LocalDataSource
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class RoomDataSource: LocalDataSource {

    override suspend fun favoriteNews(news: News): Result<Boolean> {
        TODO("Not yet implemented")
    }
}