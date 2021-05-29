package com.dicoding.picodiploma.core.data

import com.dicoding.picodiploma.core.data.source.local.LocalDataSource
import com.dicoding.picodiploma.core.data.source.remote.ApiResponse
import com.dicoding.picodiploma.core.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.core.data.source.remote.response.MovieResponse
import com.dicoding.picodiploma.core.domain.model.Movie
import com.dicoding.picodiploma.core.domain.repository.IRepository
import com.dicoding.picodiploma.core.utils.AppExecutors
import com.dicoding.picodiploma.core.utils.DataMapper
import com.dicoding.picodiploma.core.utils.NetworkBoundResource
import com.dicoding.picodiploma.core.data.source.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class Repository(private val remoteDataSource: RemoteDataSource,
                 private val localDataSource: LocalDataSource,
                 private val appExecutors: AppExecutors) : IRepository {


    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
                override fun loadFromDB(): Flow<List<Movie>> {
                    return localDataSource.getAllMovies().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()
                        //true
                // ganti dengan true jika ingin selalu mengambil data dari internet

                override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                        remoteDataSource.getAllMovies()

                override suspend fun saveCallResult(data: List<MovieResponse>) {
                    val tourismList = DataMapper.mapResponsesToEntities(data)
                    localDataSource.insertMovies(tourismList)
                }
            }.asFlow()

    override fun getFavoritedMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoritedMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }
}