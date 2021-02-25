package com.augusto.mesanews.cleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.augusto.mesanews.cleanarchitecture.data.local.converter.DateConverter
import com.augusto.mesanews.cleanarchitecture.data.local.dao.FavoriteNewsDao
import com.augusto.mesanews.cleanarchitecture.data.local.model.FavoriteNews

@Database(entities = [FavoriteNews::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class Database(): RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao
}