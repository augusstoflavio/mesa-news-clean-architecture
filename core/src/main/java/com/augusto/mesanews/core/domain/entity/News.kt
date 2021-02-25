package com.augusto.mesanews.core.domain.entity

import java.util.*

data class News (
    var title: String,
    var description: String,
    var content: String,
    var author: String,
    var publishedAt: Date,
    var highlight: Boolean,
    var url: String,
    var imageUrl: String,
    var favorite: Boolean
)