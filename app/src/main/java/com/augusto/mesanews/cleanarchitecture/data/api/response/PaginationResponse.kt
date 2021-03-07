package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json

data class PaginationResponse(
    @field:Json(name = "current_page")
    var currentPage: Int,
    @field:Json(name = "per_page")
    var perPage: Int,
    @field:Json(name = "total_pages")
    var totalPages: Int,
    @field:Json(name = "total_items")
    var totalItems: Int
)
