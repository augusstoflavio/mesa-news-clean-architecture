package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.data.api.service.AuthService
import com.augusto.mesanews.cleanarchitecture.data.api.service.NewsService
import com.augusto.mesanews.core.domain.entity.News
import okhttp3.OkHttpClient
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 120L
private const val BASE_URL = "https://mesa-news-api.herokuapp.com/"
private val RETROFIT = StringQualifier("retrofit")
private val MOSHI = StringQualifier("moshi")
private val CLIENT = StringQualifier("client")

val apiModule = module {

    single(RETROFIT) {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get(CLIENT))
                .addConverterFactory(get(MOSHI))
                .build()
    }

    single(CLIENT) {
        val httpClientBuilder = OkHttpClient.Builder()
//                .addInterceptor(get<Interceptor>(connectionInterceptor))
//                .addInterceptor(get<Interceptor>(wsInterceptor))
//                .addInterceptor(get<Interceptor>(wsResponseInterceptor))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

//        if (BuildConfig.DEBUG) {
//            httpClientBuilder.addInterceptor(get<Interceptor>(logInterceptor))
//        }

        httpClientBuilder.build()
    }

    single(MOSHI) {
        MoshiConverterFactory.create()
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