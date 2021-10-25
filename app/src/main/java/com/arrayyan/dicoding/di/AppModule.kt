package com.arrayyan.dicoding.di

import com.arrayyan.core.domain.usecase.ContentInteractor
import com.arrayyan.core.domain.usecase.ContentUseCase
import com.arrayyan.dicoding.fragment.viewmodel.MoviesViewModel
import com.arrayyan.dicoding.fragment.viewmodel.TvShowsViewModel
import com.arrayyan.dicoding.ui.detail.DetailContentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ContentUseCase> { ContentInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailContentViewModel(get()) }
}