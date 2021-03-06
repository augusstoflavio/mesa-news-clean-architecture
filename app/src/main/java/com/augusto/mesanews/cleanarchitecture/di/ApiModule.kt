package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.data.api.interceptor.CheckConnectionInterceptor
import com.augusto.mesanews.cleanarchitecture.data.api.interceptor.CheckResponseInterceptor
import com.augusto.mesanews.cleanarchitecture.data.api.interceptor.ConfigRequestInterceptor
import com.augusto.mesanews.cleanarchitecture.data.api.service.AuthService
import com.augusto.mesanews.cleanarchitecture.data.api.service.NewsService
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val TIMEOUT = 120L
private const val BASE_URL = "https://mesa-news-api.herokuapp.com/v1/"
private val RETROFIT = StringQualifier("retrofit")
private val MOSHI = StringQualifier("moshi")
private val CLIENT = StringQualifier("client")
private val CHECK_CONNECTION_INTERCEPTOR = StringQualifier("checkConnectionInterceptor")
private val CHECK_RESPONSE_INTERCEPTOR = StringQualifier("checkResponseInterceptor")
private val CONFIG_REQUEST_INTERCEPTOR = StringQualifier("configRequestInterceptor")
private val LOG_INTERCEPTOR = StringQualifier("logInterceptor")

val apiModule = module {

    single(RETROFIT) {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get(CLIENT))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    single(CLIENT) {
        val httpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(get<Interceptor>(LOG_INTERCEPTOR))
                .addInterceptor(get<Interceptor>(CHECK_CONNECTION_INTERCEPTOR))
                .addInterceptor(get<Interceptor>(CHECK_RESPONSE_INTERCEPTOR))
                .addInterceptor(get<Interceptor>(CONFIG_REQUEST_INTERCEPTOR))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.build()
    }

    single<Interceptor>(CHECK_CONNECTION_INTERCEPTOR) { CheckConnectionInterceptor() }
    single<Interceptor>(CHECK_RESPONSE_INTERCEPTOR) { CheckResponseInterceptor() }
    single<Interceptor>(CONFIG_REQUEST_INTERCEPTOR) { ConfigRequestInterceptor(get()) }
    single<Interceptor>(LOG_INTERCEPTOR) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single(MOSHI) {
        Moshi.Builder()
            .build()
    }

    single<AuthService> {
        val retrofit: Retrofit = get(RETROFIT)
        retrofit.create(AuthService::class.java)
    }

    single<NewsService> {
        val retrofit: Retrofit = get(RETROFIT)
        retrofit.create(NewsService::class.java)
    }
}