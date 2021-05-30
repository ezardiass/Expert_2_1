package com.dicoding.picodiploma.architecturecomponentsub1.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.core.domain.model.Movie
import com.dicoding.picodiploma.core.domain.usecase.MovieUseCase

class DetailMoviesViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
        fun setFavoriteMovies(movie: Movie, newState:Boolean) {
            movieUseCase.setFavoriteMovie(movie, newState)
        }
    }

