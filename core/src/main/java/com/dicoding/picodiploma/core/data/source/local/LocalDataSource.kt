package com.dicoding.picodiploma.core.data.source.local

import com.dicoding.picodiploma.core.data.room.FilmDao
import com.dicoding.picodiploma.core.data.room.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val filmDao: FilmDao) {


    fun getAllMovies(): Flow<List<MovieEntity>> = filmDao.getMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = filmDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        filmDao.updateMovie(movie)
    }

    fun getFavoritedMovie():Flow<List<MovieEntity>> =
        filmDao.getFavoritedMovie()

}