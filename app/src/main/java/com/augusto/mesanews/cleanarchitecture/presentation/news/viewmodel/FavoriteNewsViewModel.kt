package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsToPresentation
import com.augusto.mesanews.core.domain.entity.Result

class FavoriteNewsViewModel(private val useCases: UseCases): BaseViewModel() {

    private val _news = MutableLiveData<List<NewsPresentation>>()
    val news: LiveData<List<NewsPresentation>> = _news

    init {
        getFavorites()
    }

    private fun getFavorites() {
        run {
            when (val response = useCases.getFavoriteNews()) {
                is Result.Success -> {
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
}