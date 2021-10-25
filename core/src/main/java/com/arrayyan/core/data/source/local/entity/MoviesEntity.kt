package com.arrayyan.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieFavoriteTable")
data class MoviesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "cover")
    val cover: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "desc")
    val desc: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Double,

    @ColumnInfo(name = "production_countries")
    val productionCountries: String,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)