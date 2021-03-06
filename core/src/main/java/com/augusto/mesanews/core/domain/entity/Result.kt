package com.augusto.mesanews.core.domain.entity

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Failure(val error: Error) : Result<Nothing>()

    data class Error(
        var message: String,
        var code: Int? = null
    )
}