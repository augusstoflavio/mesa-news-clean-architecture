package com.augusto.mesanews.core.data.dataSource

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News

interface LocalDataSource {

    suspend fun favoriteNews(news: News): Result<Boolean>
}