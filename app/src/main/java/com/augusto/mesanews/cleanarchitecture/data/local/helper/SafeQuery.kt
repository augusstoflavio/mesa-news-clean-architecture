package com.augusto.mesanews.cleanarchitecture.data.local.helper

import com.augusto.mesanews.core.domain.entity.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T: Any> safeQuery(call: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.Success(call.invoke())
        } catch (throwable: Throwable) {
            Result.Failure(
                Result.Error("Data base error: "+throwable.message)
            )
        }
    }
}
