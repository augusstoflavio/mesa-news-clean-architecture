package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import com.augusto.mesanews.core.domain.entity.News

class GetNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(currentPage: Int): Result<List<News>> {
        return newsRepository.get(currentPage)
    }
}