package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import com.augusto.mesanews.core.domain.entity.News

class FavoriteNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(news: News): Result<Boolean> {
        return newsRepository.favorite(news)
    }
}