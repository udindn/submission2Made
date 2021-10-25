package com.arrayyan.core.utils

import com.arrayyan.core.data.source.local.entity.MoviesEntity
import com.arrayyan.core.data.source.local.entity.TvShowsEntity
import com.arrayyan.core.data.source.remote.response.ContentResponse
import com.arrayyan.core.domain.model.ContentModel

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<ContentResponse>): List<MoviesEntity> {
        val movieList = ArrayList<MoviesEntity>()
        input.map {
            val movie = MoviesEntity(
                id = it.id,
                cover = it.posterPath.toString(),
                title = it.title.toString(),
                year = parseDateToYear(it.releaseDate.toString()).toString(),
                desc = it.overview.toString(),
                releaseDate = it.releaseDate.toString(),
                runtime = it.runtime ?: 0,
                popularity = it.popularity ?: 0.0,
                originalLanguage = it.originalLanguage.toString(),
                voteAverage = it.voteAverage ?: 0.0,
                voteCount = it.voteCount ?: 0.0,
                productionCountries = it.productionCountries?.get(0).toString(),
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvResponsesToEntities(input: List<ContentResponse>): List<TvShowsEntity> {
        val tvShowsList = ArrayList<TvShowsEntity>()
        input.map {
            val tvShow = TvShowsEntity(
                id = it.id,
                cover = it.posterPath.toString(),
                title = it.name.toString(),
                year = parseDateToYear(it.firstAirDate.toString()).toString(),
                desc = it.overview.toString(),
                firstAirDate = it.firstAirDate.toString(),
                runtime = it.runtime ?: 0,
                popularity = it.popularity ?: 0.0,
                originalLanguage = it.originalLanguage.toString(),
                voteAverage = it.voteAverage ?: 0.0,
                voteCount = it.voteCount ?: 0.0,
                createdBy = it.productionCountries?.get(0).toString(),
                isFavorite = false
            )
            tvShowsList.add(tvShow)
        }
        return tvShowsList
    }

    fun mapMovieEntitiesToDomain(input: List<MoviesEntity>): List<ContentModel> =
        input.map {
            ContentModel(
                id = it.id,
                posterPath = it.cover,
                title = it.title,
                overview = it.desc,
                releaseDate = it.releaseDate,
                runtime = it.runtime,
                popularity = it.popularity,
                originalLanguage = it.originalLanguage,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                productionCountries = it.productionCountries,
                isFavorite = it.isFavorite
            )
        }

    fun mapTvEntitiesToDomain(input: List<TvShowsEntity>): List<ContentModel> =
        input.map {
            ContentModel(
                id = it.id,
                posterPath = it.cover,
                title = it.title,
                firstAirDate = it.firstAirDate,
                overview = it.desc,
                runtime = it.runtime,
                popularity = it.popularity,
                originalLanguage = it.originalLanguage,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                createdBy = it.createdBy,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToMoviesEntity(input: ContentModel) = MoviesEntity(
        id = input.id,
        cover = input.posterPath.toString(),
        title = input.title.toString(),
        year = parseDateToYear(input.releaseDate.toString()).toString(),
        desc = input.overview.toString(),
        releaseDate = input.releaseDate.toString(),
        runtime = input.runtime ?: 0,
        popularity = input.popularity ?: 0.0,
        originalLanguage = input.originalLanguage.toString(),
        voteAverage = input.voteAverage ?: 0.0,
        voteCount = input.voteCount ?: 0.0,
        productionCountries = input.productionCountries.toString(),
        isFavorite = input.isFavorite
    )

    fun mapDomainToTvShowsEntity(input: ContentModel) = TvShowsEntity(
        id = input.id,
        cover = input.posterPath.toString(),
        title = input.title.toString(),
        year = parseDateToYear(input.firstAirDate.toString()).toString(),
        desc = input.overview.toString(),
        firstAirDate = input.firstAirDate.toString(),
        runtime = input.runtime ?: 0,
        popularity = input.popularity ?: 0.0,
        originalLanguage = input.originalLanguage.toString(),
        voteAverage = input.voteAverage ?: 0.0,
        voteCount = input.voteCount ?: 0.0,
        createdBy = input.createdBy.toString(),
        isFavorite = input.isFavorite
    )
}