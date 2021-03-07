package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json

data class GetNewsResponse(
    @field:Json(name = "pagination")
    var pagination: PaginationResponse,
    @field:Json(name = "data")
    var data: List<NewsResponse>
)
