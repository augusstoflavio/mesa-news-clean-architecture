package com.augusto.mesanews.cleanarchitecture.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "favorite_news"
)
data class FavoriteNews(
    @PrimaryKey(autoGenerate = false)
    var url: String,
    var title: String,
    var description: String,
    var content: String,
    var author: String,
    var publishedAt: Date,
    var highlight: Boolean,
    var imageUrl: String,
    var favorite: Boolean
)
