package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsToPresentation
import com.augusto.mesanews.core.domain.entity.Result

class NewsHomeViewModel(private val useCases: UseCases): BaseViewModel() {

    private var _currentPage = 1

    private val _highlights = MutableLiveData<List<NewsPresentation>>()
    val highlights: LiveData<List<NewsPresentation>> = _highlights

    private val _news = MutableLiveData<List<NewsPresentation>>()
    val news: LiveData<List<NewsPresentation>> = _news

    init {
        _news.value = listOf()
    }

    fun getHighlights() {
        run {
            when (val response = useCases.getHighlights()) {
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
            when (val response = useCases.getNews(_currentPage)) {
                is Result.Success -> {
                    val newList = response.data.map {
                        NewsToPresentation.converter(it)
                    }
                    var currentList = _news.value
                    currentList = currentList!!.toMutableList()
                    currentList.addAll(newList)
                    _news.postValue(currentList!!)

                    _currentPage += 1
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }

        }
    }
}