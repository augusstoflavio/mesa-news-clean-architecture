package com.augusto.mesanews.cleanarchitecture.presentation.news.presentation

import java.io.Serializable

data class NewsPresentation(
        val imageUrl: String?,
        val title: String,
        val content: String,
        val date: String,
        val url: String,
        val isFavorite: Boolean
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (other !is NewsPresentation) {
            return false
        }

        return other.url == url
    }

    override fun hashCode(): Int {
        var result = imageUrl?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + isFavorite.hashCode()
        return result
    }
}
