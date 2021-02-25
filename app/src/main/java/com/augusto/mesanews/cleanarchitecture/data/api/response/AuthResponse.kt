package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json

data class AuthResponse(
    @Json(name = "token")
    var token: String,
)
