package com.dicoding.picodiploma.favorite.di


import com.dicoding.picodiploma.favorite.viewmodel.FavoritedMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoriteModule {

    val viewModelFavModule = module {
        viewModel { FavoritedMoviesViewModel(get()) }
    }
}