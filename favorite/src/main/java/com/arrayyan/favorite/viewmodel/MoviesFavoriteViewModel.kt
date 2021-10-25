package com.arrayyan.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrayyan.core.domain.usecase.ContentUseCase

class MoviesFavoriteViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val favoriteMovie = contentUseCase.getFavoriteMovie().asLiveData()
}