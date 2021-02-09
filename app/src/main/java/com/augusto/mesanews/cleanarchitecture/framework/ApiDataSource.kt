package com.augusto.mesanews.cleanarchitecture.framework

import com.augusto.mesanews.core.data.RemoteDataSource
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class ApiDataSource: RemoteDataSource {

    override suspend fun getNews(currentPage: Int): Result<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHighlights(): Result<List<News>> {
        TODO("Not yet implemented")
    }
}