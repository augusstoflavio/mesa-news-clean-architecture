package com.augusto.mesanews.cleanarchitecture.data.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignupRequest (
    @Json(name = "name")
    var name: String,
    @Json(name = "email")
    var email: String,
    @Json(name = "password")
    var password: String,
)