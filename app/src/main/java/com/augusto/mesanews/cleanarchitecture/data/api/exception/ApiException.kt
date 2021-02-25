package com.augusto.mesanews.cleanarchitecture.data.api.exception

import com.augusto.mesanews.core.domain.entity.Result
import java.io.IOException

class ApiException(private val messageError: String, private val code: Int): IOException(), ApiError {

    override fun toError(): Result.Error {
        return Result.Error(message = messageError, code = code)
    }
}