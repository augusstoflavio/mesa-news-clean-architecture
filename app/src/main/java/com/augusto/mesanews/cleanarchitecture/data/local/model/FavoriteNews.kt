package com.augusto.mesanews.cleanarchitecture.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "favorite_news"
)
data class FavoriteNews(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var description: String,
    var content: String,
    var author: String,
    var publishedAt: Date,
    var url: String,
    var imageUrl: String
)
