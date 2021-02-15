package com.augusto.mesanews.cleanarchitecture.data.api.interceptor

import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import okhttp3.Interceptor
import okhttp3.Response

class ConfigRequestInterceptor(private val preferencesDataSource: SharedPreferencesDataSource): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, "application/json")

        if (preferencesDataSource.isLogged()) {
            builder.addHeader(HEADER_AUTHORIZATION, preferencesDataSource.getToken()!!)
        }

        return chain.proceed(builder.build())
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_CONTENT_TYPE = "Content-Type"
    }
}