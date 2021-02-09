package com.augusto.mesanews.core.interactors

import com.augusto.mesanews.core.data.NewsRepository
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class GetHighlights(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(): Result<List<News>> {
        return newsRepository.getHighlights()
    }
}