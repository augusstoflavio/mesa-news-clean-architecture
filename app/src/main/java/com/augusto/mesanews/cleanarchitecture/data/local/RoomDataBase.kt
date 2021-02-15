package com.augusto.mesanews.cleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.augusto.mesanews.cleanarchitecture.data.local.dao.FavoriteNewsDao
import com.augusto.mesanews.cleanarchitecture.data.local.model.FavoriteNews

@Database(entities = [FavoriteNews::class], version = 1)
abstract class RoomDataBase(): RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao
}