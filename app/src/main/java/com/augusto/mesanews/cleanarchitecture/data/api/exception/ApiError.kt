package com.augusto.mesanews.cleanarchitecture.data.api.exception

import com.augusto.mesanews.core.domain.entity.Result

interface ApiError {

    fun toError(): Result.Error
}