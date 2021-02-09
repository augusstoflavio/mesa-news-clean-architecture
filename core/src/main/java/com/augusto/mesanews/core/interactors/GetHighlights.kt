package com.augusto.mesanews.core.interactors

import com.augusto.mesanews.core.data.RemoteDataSource
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class GetHighlights(private val remoteDataSource: RemoteDataSource) {

    suspend operator fun invoke(): Result<List<News>> {
        return remoteDataSource.getHighlights()
    }
}