package com.arrayyan.core.domain.usecase

import com.arrayyan.core.domain.model.ContentModel
import com.arrayyan.core.domain.repository.IContentRepository

class ContentInteractor(private val contentRepository: IContentRepository) : ContentUseCase {

    override fun getAllMovie() = contentRepository.getAllMovie()

    override fun getFavoriteMovie() = contentRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: ContentModel, state: Boolean) =
        contentRepository.setFavoriteMovie(movie, state)

    override fun getAllTvShow()=  contentRepository.getAllTvShow()

    override fun getFavoriteTvShow() = contentRepository.getFavoriteTvShow()

    override fun setFavoriteTvShow(tvShow: ContentModel, state: Boolean) =
        contentRepository.setFavoriteTvShow(tvShow, state)
}