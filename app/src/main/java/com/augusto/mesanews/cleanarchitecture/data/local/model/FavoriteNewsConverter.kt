package com.augusto.mesanews.cleanarchitecture.data.local.model

import com.augusto.mesanews.core.domain.entity.News
import java.util.*

object FavoriteNewsConverter {

    fun toNews(favoriteNews: FavoriteNews): News {
        return News(
                title = favoriteNews.title,
                url = favoriteNews.url,
                description = favoriteNews.description,
                content = favoriteNews.content,
                author = favoriteNews.author,
                publishedAt = favoriteNews.publishedAt,
                highlight = favoriteNews.highlight,
                imageUrl = favoriteNews.imageUrl,
                favorite = favoriteNews.favorite,
        )
    }

    fun fromNews(news: News): FavoriteNews {
        return FavoriteNews(
                title = news.title,
                url = news.url,
                description = news.description,
                content = news.content,
                author = news.author,
                publishedAt = news.publishedAt,
                highlight = news.highlight,
                imageUrl = news.imageUrl,
                favorite = news.favorite,
        )
    }
}