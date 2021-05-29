package com.dicoding.picodiploma.core.domain.usecase

import com.dicoding.picodiploma.core.domain.repository.IRepository
import com.dicoding.picodiploma.core.domain.model.Movie


class Interactor(private val repository: IRepository): MovieUseCase {

    override fun getAllMovie() = repository.getAllMovies()

    override fun getFavoriteMovie() = repository.getFavoritedMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = repository.setMovieFavorite(movie, state)
}