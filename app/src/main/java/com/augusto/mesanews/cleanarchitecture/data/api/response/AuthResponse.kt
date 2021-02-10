package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "token")
    var token: String,
)
