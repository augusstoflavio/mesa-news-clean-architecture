package com.augusto.mesanews.cleanarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.augusto.mesanews.cleanarchitecture.data.local.model.FavoriteNews

@Dao
interface FavoriteNewsDao {

    @Query("SELECT * FROM favorite_news")
    suspend fun getAll(): List<FavoriteNews>

    @Query("SELECT * FROM favorite_news WHERE url = :url")
    suspend fun getByUrl(url: String): List<FavoriteNews>

    @Insert
    suspend fun save(favoriteNews: FavoriteNews)

    @Delete
    suspend fun delete(favoriteNews: FavoriteNews)
}