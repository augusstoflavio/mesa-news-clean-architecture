package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetNewsResponse(
    @Json(name = "pagination")
    var pagination: PaginationResponse,
    @Json(name = "data")
    var data: List<NewsResponse>
)