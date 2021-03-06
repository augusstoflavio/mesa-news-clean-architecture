package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json

data class GetNewsResponse(
    @Json(name = "pagination")
    var pagination: PaginationResponse,
    @Json(name = "data")
    var data: List<NewsResponse>
)
