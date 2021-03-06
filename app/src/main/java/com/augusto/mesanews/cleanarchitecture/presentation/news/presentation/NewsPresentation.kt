package com.augusto.mesanews.cleanarchitecture.presentation.news.presentation

data class NewsPresentation(
        val imageUrl: String?,
        val title: String,
        val content: String,
        val date: String,
        val isFavorite: Boolean
)
