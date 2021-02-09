package com.augusto.mesanews.core.interactors

import com.augusto.mesanews.core.data.NewsRepository
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class GetNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(currentPage: Int): Result<List<News>> {
        return newsRepository.get(currentPage)
    }
}