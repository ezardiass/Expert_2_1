package com.dicoding.picodiploma.core.domain.usecase

import com.dicoding.picodiploma.core.domain.model.Movie
import com.dicoding.picodiploma.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}