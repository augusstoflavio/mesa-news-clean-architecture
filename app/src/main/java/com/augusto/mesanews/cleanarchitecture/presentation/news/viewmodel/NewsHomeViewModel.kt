package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.core.domain.entity.Result

class NewsHomeViewModel(private val useCases: UseCases): BaseViewModel() {

    private val _highlights = MutableLiveData<List<NewsPresentation>>()
    val highlights: LiveData<List<NewsPresentation>> = _highlights

    private val _news = MutableLiveData<List<NewsPresentation>>()
    val news: LiveData<List<NewsPresentation>> = _news

    fun getHighlights() {
        run {
            when (val response = useCases.getHighlights()) {
                is Result.Success -> {
                    _highlights.postValue(response.data.map {
                        NewsPresentation(
                            imageUrl = it.imageUrl,
                            title = it.title,
                            content = it.content,
                            isFavorite = it.favorite,
                            date = "2 horas"
                        )
                    })
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }

        }
    }

    fun getNews(currentPage: Int) {
        run {
            when (val response = useCases.getNews(currentPage)) {
                is Result.Success -> {
                    _news.postValue(response.data.map {
                        NewsPresentation(
                            imageUrl = it.imageUrl,
                            title = it.title,
                            content = it.content,
                            isFavorite = it.favorite,
                            date = "2 horas"
                        )
                    })
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }

        }
    }
}