package com.augusto.mesanews.cleanarchitecture.data.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SigninRequest(
    @Json(name = "email")
    var email: String,
    @Json(name = "password")
    var password: String,
)
