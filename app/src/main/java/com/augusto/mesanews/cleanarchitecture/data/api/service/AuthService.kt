package com.augusto.mesanews.cleanarchitecture.data.api.service

import com.augusto.mesanews.cleanarchitecture.data.api.request.SigninRequest
import com.augusto.mesanews.cleanarchitecture.data.api.request.SignupRequest
import com.augusto.mesanews.cleanarchitecture.data.api.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("client/auth/signin")
    suspend fun signin(@Body signinRequest: SigninRequest): Response<AuthResponse>

    @POST("client/auth/signup")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<AuthResponse>
}