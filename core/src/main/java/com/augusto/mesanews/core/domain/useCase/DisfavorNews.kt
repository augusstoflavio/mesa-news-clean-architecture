package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository

class DisfavorNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(news: News): Result<Boolean> {
        return newsRepository.disfavor(news)
    }
}