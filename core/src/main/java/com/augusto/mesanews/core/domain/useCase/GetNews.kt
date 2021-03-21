package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import com.augusto.mesanews.core.domain.entity.News

class GetNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(currentPage: Int): Result<List<News>> {
        val result = newsRepository.get(currentPage)
        if (result is Result.Success) {
            return Result.Success(fillFavorite(result.data))
        }

        return result
    }

    private suspend fun fillFavorite(news: List<News>): List<News> {
        return news.map {
            it.favorite = checkIsFavorite(it)
            return@map it
        }
    }

    private suspend fun checkIsFavorite(url: News): Boolean {
        val result = newsRepository.checkIsFavorite(url)
        return result is Result.Success && result.data
    }
}