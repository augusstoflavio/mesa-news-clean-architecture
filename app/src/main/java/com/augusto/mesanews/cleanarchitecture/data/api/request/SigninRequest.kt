package com.augusto.mesanews.cleanarchitecture.data.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SigninRequest(
    @Json(name = "name")
    var name: String,
    @Json(name = "email")
    var email: String,
)
