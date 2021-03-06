package com.augusto.mesanews.cleanarchitecture.data.api.service

import com.augusto.mesanews.cleanarchitecture.data.api.response.GetHighlightsResponse
import com.augusto.mesanews.cleanarchitecture.data.api.response.GetNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("client/news")
    suspend fun getNews(@Query("current_page") currentPage: Int): Response<GetNewsResponse>

    @GET("client/news/highlights")
    suspend fun getHighlights(): Response<GetHighlightsResponse>
}