package com.arrayyan.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.arrayyan.core.data.source.local.entity.MoviesEntity
import com.arrayyan.core.data.source.local.entity.TvShowsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM MovieFavoriteTable")
    fun getListMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM TvFavoriteTable")
    fun getListTvShows(): Flow<List<TvShowsEntity>>

    @Query("SELECT * FROM MovieFavoriteTable WHERE isFavorite = 1")
    fun getListFavoriteMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM TvFavoriteTable WHERE isFavorite = 1")
    fun getListFavoriteTvShows(): Flow<List<TvShowsEntity>>

    @Query("SELECT * FROM MovieFavoriteTable WHERE id = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM TvFavoriteTable WHERE id = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowsEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Update
    fun updateTvShow(tvShows: TvShowsEntity)
}
