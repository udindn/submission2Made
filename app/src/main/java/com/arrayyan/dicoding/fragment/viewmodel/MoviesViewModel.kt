package com.arrayyan.dicoding.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arrayyan.core.domain.usecase.ContentUseCase

class MoviesViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val movie = contentUseCase.getAllMovie().asLiveData()
}
