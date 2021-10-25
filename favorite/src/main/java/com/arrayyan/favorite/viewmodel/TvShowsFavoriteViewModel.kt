package com.arrayyan.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrayyan.core.domain.usecase.ContentUseCase

class TvShowsFavoriteViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val favoriteTvShow = contentUseCase.getFavoriteTvShow().asLiveData()
}