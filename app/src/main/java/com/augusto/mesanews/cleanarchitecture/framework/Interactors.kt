package com.augusto.mesanews.cleanarchitecture.framework

import com.augusto.mesanews.core.interactors.FavoriteNews
import com.augusto.mesanews.core.interactors.GetHighlights
import com.augusto.mesanews.core.interactors.GetNews

data class Interactors (
    val getHighlights: GetHighlights,
    val getNews: GetNews,
    val favoriteNews: FavoriteNews
)