package com.dicoding.picodiploma.core.data.source.remote

import com.dicoding.picodiploma.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("top_rated?api_key=6d2f15aea09b71b1842bbb955dbb95d1&language=en-US&page=1")
   suspend fun getListMovie(): ListMovieResponse

}