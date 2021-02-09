package com.augusto.mesanews.core.interactors

import com.augusto.mesanews.core.data.NewsRepository
import com.augusto.mesanews.core.data.Result
import com.augusto.mesanews.core.domain.News

class FavoriteNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(news: News): Result<Boolean> {
        return newsRepository.favorite(news)
    }
}