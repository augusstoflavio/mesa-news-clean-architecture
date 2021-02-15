package com.augusto.mesanews.cleanarchitecture.data.api

import com.augusto.mesanews.cleanarchitecture.data.api.helper.safeCall
import com.augusto.mesanews.cleanarchitecture.data.api.request.SigninRequest
import com.augusto.mesanews.cleanarchitecture.data.api.request.SignupRequest
import com.augusto.mesanews.cleanarchitecture.data.api.service.AuthService
import com.augusto.mesanews.cleanarchitecture.data.api.service.NewsService
import com.augusto.mesanews.core.data.dataSource.RemoteDataSource
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import java.util.*

class ApiDataSource(private val authService: AuthService, private val newsService: NewsService): RemoteDataSource {

    override suspend fun getNews(currentPage: Int): Result<List<News>> = safeCall {
        val getNewsResponse = newsService.getNews(currentPage).body()
        getNewsResponse?.data?.map {
            News(
                title = it.title,
                author = it.author,
                content = it.content,
                description = it.description,
                favorite = false,
                highlight = it.highlight,
                imageUrl = it.imageUrl,
                publishedAt = Calendar.getInstance(),
                url = it.url
            )
        } ?: listOf()
    }

    override suspend fun getHighlights(): Result<List<News>> = safeCall {
        val getNewsResponse = newsService.getHighlights().body()
        getNewsResponse?.data?.map {
            News(
                title = it.title,
                author = it.author,
                content = it.content,
                description = it.description,
                favorite = false,
                highlight = it.highlight,
                imageUrl = it.imageUrl,
                publishedAt = Calendar.getInstance(),
                url = it.url
            )
        } ?: listOf()
    }

    override suspend fun signin(email: String, password: String): Result<String> = safeCall {
        val response = authService.signin(
            SigninRequest(
                email = email,
                password = password
            )
        ).body()
        response!!.token
    }

    override suspend fun signup(name: String, email: String, password: String): Result<String> = safeCall {
        val response = authService.signup(
            SignupRequest(
                name = name,
                email = email,
                password = password
            )
        ).body()
        response!!.token
    }
}