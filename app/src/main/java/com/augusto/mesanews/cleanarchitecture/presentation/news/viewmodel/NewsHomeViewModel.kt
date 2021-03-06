package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsToPresentation
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.useCase.DisfavorNews
import com.augusto.mesanews.core.domain.useCase.FavoriteNews
import com.augusto.mesanews.core.domain.useCase.GetHighlights
import com.augusto.mesanews.core.domain.useCase.GetNews
import kotlinx.coroutines.CoroutineDispatcher

class NewsHomeViewModel(
    private val getHighlightsUseCase: GetHighlights,
    private val getNewsUseCase: GetNews,
    private val favoriteNewsUseCase: FavoriteNews,
    private val disfavorNewsUseCase: DisfavorNews,
    private val defaultDispatcher: CoroutineDispatcher
): BaseViewModel(defaultDispatcher) {

    private var _currentPage = 1

    private val _highlights = MutableLiveData<List<NewsPresentation>>()
    val highlights: LiveData<List<NewsPresentation>> = _highlights

    private val _news = MutableLiveData<List<NewsPresentation>>()
    val news: LiveData<List<NewsPresentation>> = _news

    private val _updatedNews = MutableLiveData<NewsPresentation?>()
    val updatedNews: LiveData<NewsPresentation?> = _updatedNews


    private val _listNews = mutableListOf<News>()

    fun getHighlights() {
        run {
            when (val response = getHighlightsUseCase()) {
                is Result.Success -> {
                    _highlights.postValue(response.data.map {
                        NewsToPresentation.converter(it)
                    })
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }

        }
    }

    fun getNews() {
        run {
            when (val response = getNewsUseCase(_currentPage)) {
                is Result.Success -> {
                    val newList = response.data.map {
                        NewsToPresentation.converter(it)
                    }
                    _news.postValue(newList)
                    _listNews.addAll(response.data)

                    _currentPage += 1
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }
        }
    }

    fun favoriteNews(newsPresentation: NewsPresentation, favorite: Boolean) {
        run {
            val news: News = _listNews.firstOrNull() {
                it.url == newsPresentation.url
            } ?: return@run

            news.favorite = favorite

            val response = if (favorite) {
                favoriteNewsUseCase(news)
            } else {
                disfavorNewsUseCase(news)
            }

            when (response) {
                is Result.Success -> {
                    updateListNewsPresentation(NewsToPresentation.converter(news))
                }
                is Result.Failure -> {
                    updateListNewsPresentation(newsPresentation)
                    notifyFailure(response)
                }
            }
        }
    }

    private fun updateListNewsPresentation(news: NewsPresentation) {
        _updatedNews.postValue(news)
    }
}