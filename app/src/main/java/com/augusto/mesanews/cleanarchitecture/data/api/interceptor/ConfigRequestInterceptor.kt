package com.augusto.mesanews.cleanarchitecture.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ConfigRequestInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, "application/json")

        //TODO add authorization

        return chain.proceed(builder.build())
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_CONTENT_TYPE = "Content-Type"
    }
}