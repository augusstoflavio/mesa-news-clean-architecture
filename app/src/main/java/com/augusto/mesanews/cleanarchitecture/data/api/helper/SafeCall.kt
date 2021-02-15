package com.augusto.mesanews.cleanarchitecture.data.api.helper

import com.augusto.mesanews.cleanarchitecture.data.api.exception.ApiError
import com.augusto.mesanews.core.domain.entity.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T: Any> safeCall(call: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.Success(call.invoke())
        } catch (throwable: Throwable) {
            if (throwable is ApiError) {
                Result.Failure(
                    throwable.toError()
                )
            } else {
                Result.Failure(
                    Result.Error(throwable.message ?: "")
                )
            }
        }
    }
}
