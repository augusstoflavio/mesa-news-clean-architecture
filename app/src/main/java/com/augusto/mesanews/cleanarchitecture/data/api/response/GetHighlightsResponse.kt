package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetHighlightsResponse(
    @Json(name = "data")
    var data: List<NewsResponse>,
)