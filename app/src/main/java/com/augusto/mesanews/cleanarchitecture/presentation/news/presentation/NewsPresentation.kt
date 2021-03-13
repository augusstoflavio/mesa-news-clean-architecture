package com.augusto.mesanews.cleanarchitecture.presentation.news.presentation

import java.io.Serializable

data class NewsPresentation(
        val imageUrl: String?,
        val title: String,
        val content: String,
        val date: String,
        val url: String,
        val isFavorite: Boolean
): Serializable
