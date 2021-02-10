package com.augusto.mesanews.core.data.dataSource

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News

interface RemoteDataSource {

    suspend fun getNews(currentPage: Int): Result<List<News>>

    suspend fun getHighlights(): Result<List<News>>

    suspend fun signin(email: String, password: String): Result<String>

    suspend fun signup(name: String, email: String, password: String): Result<String>
}