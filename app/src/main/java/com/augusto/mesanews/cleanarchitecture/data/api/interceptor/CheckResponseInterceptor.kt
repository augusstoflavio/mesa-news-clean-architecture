package com.augusto.mesanews.cleanarchitecture.data.api.interceptor

import com.augusto.mesanews.cleanarchitecture.data.api.exception.ApiException
import okhttp3.Interceptor
import okhttp3.Response

class CheckResponseInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            throw ApiException()
        }
        return response
    }

}
