package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    @Json(name = "current_page")
    var currentPage: Int,
    @Json(name = "per_page")
    var perPage: Int,
    @Json(name = "total_pages")
    var totalPages: Int,
    @Json(name = "total_items")
    var totalItems: Int
)
