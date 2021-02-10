package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse (
    @Json(name = "title")
    var title: String,
    @Json(name = "description")
    var description: String,
    @Json(name = "content")
    var content: String,
    @Json(name = "author")
    var author: String,
    @Json(name = "published_at")
    var published_at: String,
    @Json(name = "highlight")
    var highlight: Boolean,
    @Json(name = "url")
    var url: String,
    @Json(name = "image_url")
    var imageUrl: String,
)