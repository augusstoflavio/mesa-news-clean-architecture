package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository

class Signin(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String): Result<String> {
        return authRepository.signin(email, password)
    }
}