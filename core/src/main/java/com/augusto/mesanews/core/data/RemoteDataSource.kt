package com.augusto.mesanews.core.data

import com.augusto.mesanews.core.domain.News

interface RemoteDataSource {

    suspend fun getNews(currentPage: Int): Result<List<News>>

    suspend fun getHighlights(): Result<List<News>>
}