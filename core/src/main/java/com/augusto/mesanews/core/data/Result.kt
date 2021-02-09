package com.augusto.mesanews.core.data

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Failure(val error: Error) : Result<Nothing>()

    companion object {
        val SUCCESS = Success(Unit)
    }
}