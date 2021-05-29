package com.dicoding.picodiploma.core.domain.repository



import com.dicoding.picodiploma.core.domain.model.Movie
import com.dicoding.picodiploma.core.data.source.Resource
import kotlinx.coroutines.flow.Flow


interface IRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoritedMovie(): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}
