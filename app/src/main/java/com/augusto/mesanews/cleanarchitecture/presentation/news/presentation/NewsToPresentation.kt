package com.augusto.mesanews.cleanarchitecture.presentation.news.presentation

import android.annotation.SuppressLint
import com.augusto.mesanews.core.domain.entity.News
import java.text.SimpleDateFormat

object NewsToPresentation {

    @SuppressLint("SimpleDateFormat")
    private val FORMAT = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    fun converter(news: News): NewsPresentation {
        return NewsPresentation(
                imageUrl = news.imageUrl,
                title = news.title,
                content = news.content,
                isFavorite = news.favorite,
                url = news.url,
                date = FORMAT.format(news.publishedAt)
        )
    }
}