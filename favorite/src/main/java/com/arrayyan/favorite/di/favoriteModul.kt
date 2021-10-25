package com.arrayyan.favorite.di

import com.arrayyan.favorite.viewmodel.MoviesFavoriteViewModel
import com.arrayyan.favorite.viewmodel.TvShowsFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MoviesFavoriteViewModel(get()) }
    viewModel { TvShowsFavoriteViewModel(get()) }
}