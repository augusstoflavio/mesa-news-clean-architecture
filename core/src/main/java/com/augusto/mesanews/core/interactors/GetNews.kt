package com.augusto.mesanews.core.interactors

import com.augusto.mesanews.core.data.RemoteDataSource
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class GetNews(private val remoteDataSource: RemoteDataSource) {

    suspend operator fun invoke(currentPage: Int): Result<List<News>> {
        return remoteDataSource.getNews(currentPage)
    }
}