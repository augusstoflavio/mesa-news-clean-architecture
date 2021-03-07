package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json

data class GetHighlightsResponse(
    @field:Json(name = "data")
    var data: List<NewsResponse>,
)