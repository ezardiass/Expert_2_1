package com.dicoding.picodiploma.architecturecomponentsub1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.core.domain.usecase.MovieUseCase


class MoviesViewModel (movieUseCase: MovieUseCase): ViewModel() {
    val getMovies = movieUseCase.getAllMovie().asLiveData()
}