package com.arrayyan.core.domain.usecase

import com.arrayyan.core.data.Resource
import com.arrayyan.core.domain.model.ContentModel
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    fun getAllMovie(): Flow<Resource<List<ContentModel>>>
    fun getFavoriteMovie(): Flow<List<ContentModel>>
    fun setFavoriteMovie(movie: ContentModel, state: Boolean)
    fun getAllTvShow(): Flow<Resource<List<ContentModel>>>
    fun getFavoriteTvShow(): Flow<List<ContentModel>>
    fun setFavoriteTvShow(tvShow: ContentModel, state: Boolean)
}