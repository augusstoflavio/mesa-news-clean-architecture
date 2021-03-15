package com.augusto.mesanews.core.domain.repository

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.entity.News

interface NewsRepository {
    suspend fun favorite(news: News): Result<Boolean>

    suspend fun disfavor(news: News): Result<Boolean>

    suspend fun get(currentPage: Int): Result<List<News>>

    suspend fun getHighlights(): Result<List<News>>
}