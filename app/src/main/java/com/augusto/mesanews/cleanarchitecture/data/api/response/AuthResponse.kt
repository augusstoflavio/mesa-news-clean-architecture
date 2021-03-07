package com.augusto.mesanews.cleanarchitecture.data.api.response

import com.squareup.moshi.Json

data class AuthResponse(
    @field:Json(name = "token")
    var token: String,
)
