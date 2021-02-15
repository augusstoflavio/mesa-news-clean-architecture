package com.augusto.mesanews.cleanarchitecture.data.api.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.augusto.mesanews.cleanarchitecture.App
import com.augusto.mesanews.cleanarchitecture.data.api.exception.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class CheckConnectionInterceptor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (hasConnection()) {
            throw NoConnectionException()
        }

        return chain.proceed(chain.request())
    }

    private fun hasConnection(): Boolean {
        val cm = App.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetwork != null
    }
}
