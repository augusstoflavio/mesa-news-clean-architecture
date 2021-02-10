package com.augusto.mesanews.cleanarchitecture.data.api

import com.augusto.mesanews.core.data.dataSource.RemoteDataSource
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News

class ApiDataSource: RemoteDataSource {

    override suspend fun getNews(currentPage: Int): Result<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHighlights(): Result<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun signin(email: String, password: String): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun signup(name: String, email: String, password: String): Result<String> {
        TODO("Not yet implemented")
    }
}