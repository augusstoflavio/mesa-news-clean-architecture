package com.augusto.mesanews.core.data.repository

import com.augusto.mesanews.core.data.dataSource.RemoteDataSource
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository

class AuthRepositoryImpl(private val remoteDataSource: RemoteDataSource) : AuthRepository {

    override suspend fun signin(email: String, password: String): Result<String> {
        return remoteDataSource.signin(email, password)
    }

    override suspend fun signup(name: String, email: String, password: String): Result<String> {
        return remoteDataSource.signup(name, email, password)
    }
}