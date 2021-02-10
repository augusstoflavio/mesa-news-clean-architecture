package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import com.augusto.mesanews.core.domain.entity.News

class GetHighlights(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(): Result<List<News>> {
        return newsRepository.getHighlights()
    }
}