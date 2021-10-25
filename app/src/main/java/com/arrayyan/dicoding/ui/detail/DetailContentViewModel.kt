package com.arrayyan.dicoding.ui.detail

import androidx.lifecycle.ViewModel
import com.arrayyan.core.domain.model.ContentModel
import com.arrayyan.core.domain.usecase.ContentUseCase

class DetailContentViewModel(private val contentUseCase: ContentUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: ContentModel, newStatus: Boolean) =
        contentUseCase.setFavoriteMovie(movie, newStatus)
    fun setFavoriteTvShow(tvShow: ContentModel, newStatus: Boolean) =
        contentUseCase.setFavoriteTvShow(tvShow, newStatus)
}