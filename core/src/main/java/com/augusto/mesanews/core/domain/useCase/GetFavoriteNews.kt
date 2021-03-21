package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository

class GetFavoriteNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(): Result<List<News>> {
        return newsRepository.getFavoriteNews()
    }
}