package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository

class Signup(private val authRepository: AuthRepository) {

    suspend operator fun invoke(name: String, email: String, password: String): Result<String> {
        return authRepository.signup(name, email, password)
    }
}