package com.augusto.mesanews.core.domain.entity

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Failure(val error: Throwable) : Result<Nothing>()

    companion object {
        val SUCCESS = Success(Unit)
    }
}