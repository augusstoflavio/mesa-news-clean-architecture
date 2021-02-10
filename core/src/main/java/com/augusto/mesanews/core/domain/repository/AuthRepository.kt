package com.augusto.mesanews.core.domain.repository

import com.augusto.mesanews.core.domain.entity.Result

interface AuthRepository {
    suspend fun signin(email: String, password: String): Result<String>

    suspend fun signup(name: String, email: String, password: String): Result<String>
}