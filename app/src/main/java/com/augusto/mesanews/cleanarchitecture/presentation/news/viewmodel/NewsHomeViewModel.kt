package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result

class NewsHomeViewModel(private val useCases: UseCases): BaseViewModel() {

    private val _highlights = MutableLiveData<List<News>>()
    val highlights: LiveData<List<News>> = _highlights

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    fun getHighlights() {
        run {
            when (val response = useCases.getHighlights()) {
                is Result.Success -> {
                    _highlights.postValue(response.data!!)
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
                    _news.postValue(response.data!!)
                }
                is Result.Failure -> {
                    notifyFailure(response)
                }
            }

        }
    }
}