package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.augusto.mesanews.core.domain.entity.News
import com.squareup.moshi.Json
import java.util.*

data class NewsResponse (
    @field:Json(name = "title")
    var title: String,
    @field:Json(name = "description")
    var description: String,
    @field:Json(name = "content")
    var content: String,
    @field:Json(name = "author")
    var author: String,
    @field:Json(name = "published_at")
    var publishedAt: Date,
    @field:Json(name = "highlight")
    var highlight: Boolean,
    @field:Json(name = "url")
    var url: String,
    @field:Json(name = "image_url")
    var imageUrl: String,
) {

    fun toData(): News {
        return News(
                title = title,
                author = author,
                content = content,
                description = description,
                favorite = false,
                highlight = highlight,
                imageUrl = imageUrl ?: "",
                publishedAt = publishedAt,
                url = url
        )
    }
}