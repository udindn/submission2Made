package com.arrayyan.core.data

import com.arrayyan.core.data.source.local.LocalDataSource
import com.arrayyan.core.data.source.remote.RemoteDataSource
import com.arrayyan.core.data.source.remote.network.ApiResponse
import com.arrayyan.core.data.source.remote.response.ContentResponse
import com.arrayyan.core.domain.model.ContentModel
import com.arrayyan.core.domain.repository.IContentRepository
import com.arrayyan.core.utils.AppExecutors
import com.arrayyan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContentRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IContentRepository {

    override fun getAllMovie(): Flow<Resource<List<ContentModel>>> =
        object : NetworkBoundResource<List<ContentModel>, List<ContentResponse>>() {
            override fun loadFromDB(): Flow<List<ContentModel>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ContentModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ContentResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<ContentResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<ContentModel>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: ContentModel, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToMoviesEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(moviesEntity, state)
        }
    }

    override fun getAllTvShow(): Flow<Resource<List<ContentModel>>> =
        object : NetworkBoundResource<List<ContentModel>, List<ContentResponse>>() {
            override fun loadFromDB(): Flow<List<ContentModel>> {
                return localDataSource.getAllTvShow().map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ContentModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ContentResponse>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<ContentResponse>) {
                val tvShowList = DataMapper.mapTvResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()

    override fun getFavoriteTvShow(): Flow<List<ContentModel>> {
        return localDataSource.getFavoriteTvShow().map {
            DataMapper.mapTvEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTvShow(tvShow: ContentModel, state: Boolean) {
        val tvShowEntity = DataMapper.mapDomainToTvShowsEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShowEntity, state)
        }
    }
}