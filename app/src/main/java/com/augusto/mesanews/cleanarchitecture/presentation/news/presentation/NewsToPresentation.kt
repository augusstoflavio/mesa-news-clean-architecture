package com.augusto.mesanews.cleanarchitecture.presentation.news.presentation

import com.augusto.mesanews.core.domain.entity.News

object NewsToPresentation {

    fun converter(news: News): NewsPresentation {
        return NewsPresentation(
                imageUrl = news.imageUrl,
                title = news.title,
                content = news.content,
                isFavorite = news.favorite,
                date = "2 horas"
        )
    }
}