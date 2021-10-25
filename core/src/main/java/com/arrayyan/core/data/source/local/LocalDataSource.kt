package com.arrayyan.core.data.source.local

import com.arrayyan.core.data.source.local.entity.MoviesEntity
import com.arrayyan.core.data.source.local.entity.TvShowsEntity
import com.arrayyan.core.data.source.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val favoriteDao: FavoriteDao) {

    fun getAllMovie(): Flow<List<MoviesEntity>> = favoriteDao.getListMovies()

    fun getFavoriteMovie(): Flow<List<MoviesEntity>> = favoriteDao.getListFavoriteMovies()

    suspend fun insertMovie(movieList: List<MoviesEntity>) = favoriteDao.insertMovies(movieList)

    fun setFavoriteMovie(movie: MoviesEntity, newState: Boolean) {
        movie.isFavorite = newState
        favoriteDao.updateMovie(movie)
    }

    fun getAllTvShow(): Flow<List<TvShowsEntity>> = favoriteDao.getListTvShows()

    fun getFavoriteTvShow(): Flow<List<TvShowsEntity>> = favoriteDao.getListFavoriteTvShows()

    suspend fun insertTvShow(tvShowList: List<TvShowsEntity>) = favoriteDao.insertTvShows(tvShowList)

    fun setFavoriteTvShow(tvShow: TvShowsEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        favoriteDao.updateTvShow(tvShow)
    }
}