package com.arrayyan.dicoding.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrayyan.core.domain.usecase.ContentUseCase

class TvShowsViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val tvShow = contentUseCase.getAllTvShow().asLiveData()
}
