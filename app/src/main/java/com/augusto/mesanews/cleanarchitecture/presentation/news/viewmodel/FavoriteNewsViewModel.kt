package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsToPresentation
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.useCase.DisfavorNews
import com.augusto.mesanews.core.domain.useCase.GetFavoriteNews
import kotlinx.coroutines.CoroutineDispatcher

class FavoriteNewsViewModel(
        private val getFavoriteNewsUserCase: GetFavoriteNews,
        private val disfavorNews: DisfavorNews,
        private val defaultDispatcher: CoroutineDispatcher
): BaseViewModel(defaultDispatcher) {

    private val _news = MutableLiveData<List<NewsPresentation>>()
    val news: LiveData<List<NewsPresentation>> = _news

    private val _listNews = mutableListOf<News>()

    fun getFavorites() {
        run {
            when (val response = getFavoriteNewsUserCase()) {
                is Result.Success -> {
                    _listNews.addAll(response.data)
                    _news.postValue(response.data.map {
                        NewsToPresentation.converter(it)
                    })
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }

        }
    }

    fun disfavorNews(newsPresentation: NewsPresentation) {
        run {
            val news: News = _listNews.firstOrNull() {
                it.url == newsPresentation.url
            } ?: return@run

            news.favorite = false

            when (val response = disfavorNews(news)) {
                is Result.Success -> {
                    removeItemList(NewsToPresentation.converter(news))
                }
                is Result.Failure -> {
                    updateItemList(newsPresentation)
                    notifyFailure(response)
                }
            }
        }
    }

    private fun updateItemList(newsPresentation: NewsPresentation) {
        val listNews = _news.value?.toMutableList() ?: mutableListOf()
        listNews[listNews.indexOf(newsPresentation)] = newsPresentation
        _news.postValue(listNews)
    }

    private fun removeItemList(newsPresentation: NewsPresentation) {
        val listNews = _news.value?.toMutableList() ?: mutableListOf()
        listNews.removeAt(listNews.indexOf(newsPresentation))
        _news.postValue(listNews)
    }
}