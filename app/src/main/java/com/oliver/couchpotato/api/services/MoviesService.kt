package com.oliver.couchpotato.api.services

import com.oliver.couchpotato.api.dto.CastResults
import com.oliver.couchpotato.api.dto.DiscoverResults
import com.oliver.couchpotato.api.dto.MovieResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Long,
        @Header("Authorization") authorization: String,
    ): Response<MovieResult>

    @GET("movie/{movieId}/credits")
     suspend fun getMovieCast(
        @Path("movieId") movieId: Long,
        @Header("Authorization") authorization: String
    ): Response<CastResults>
}