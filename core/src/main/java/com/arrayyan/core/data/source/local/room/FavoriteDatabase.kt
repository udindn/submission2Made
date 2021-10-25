package com.arrayyan.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arrayyan.core.data.source.local.entity.MoviesEntity
import com.arrayyan.core.data.source.local.entity.TvShowsEntity

@Database(entities = [MoviesEntity::class, TvShowsEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}