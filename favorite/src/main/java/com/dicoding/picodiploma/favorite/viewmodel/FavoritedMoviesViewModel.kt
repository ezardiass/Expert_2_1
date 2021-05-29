package com.dicoding.picodiploma.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.core.domain.usecase.MovieUseCase

class FavoritedMoviesViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

        val getFavoritedMovies =movieUseCase.getFavoriteMovie().asLiveData()
    }