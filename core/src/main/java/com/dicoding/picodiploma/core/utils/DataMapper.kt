package com.dicoding.picodiploma.core.utils

import com.dicoding.picodiploma.core.domain.model.Movie
import com.dicoding.picodiploma.core.data.room.MovieEntity
import com.dicoding.picodiploma.core.data.source.remote.response.MovieResponse


object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
             it.id,
            it.title,
            it.date,
            it.description,
            it.duration,
            it.image,
                    favorited = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                    it.movieId,
                    it.title,
                    it.date,
                    it.description,
                    it.duration,
                    it.image,
                    it.favorited,
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
            input.movieId,
            input.title,
            input.date,
            input.description,
            input.duration,
            input.image
    ,input.favorited
    )
}